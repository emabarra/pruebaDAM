package com.example.movilesprueba.ui.intranet

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.SearchBarDefaults.InputField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movilesprueba.viewmodel.IntranetViewModel

val PrimaryCyan = Color(0xFF00BCE4)
val InfoBoxBg = Color(0xFFE8F7FC)
val DarkText = Color(0xFF1F2937)

@Composable
                /*Obtiene la instancia del IntranetViewModel*/
fun IntranetScreen(viewModel: IntranetViewModel = viewModel()) {
                        /*Transforma el flujo de datos (StateFlow) del ViewModel en un estado de Compose.
                        * Cada vez que currentScreentState cambia, la pantalla se redibuja automáticamente.*/
    val currentScreenState by viewModel.screenState.collectAsState()
                                        /*Asigna automáticamente los textos correspondientes a tres variables simutáneas*/
    val (breadcrumb, title, subtitle) = when (currentScreenState) {
        IntranetScreenState.LOGIN -> Triple(
            "Intranet",
            "Intranet",
            "Accede a los recursos y herramientas internas de la comunidad CENAMAD."
        )
        IntranetScreenState.RECUPERAR_CONTRASEÑA -> Triple(
            "Intranet  >  Recuperación",
            "Recuperar contraseña",
            "Te ayudamos a recuperar el acceso a tu cuenta de la intranet."
        )
        IntranetScreenState.REGISTRO -> Triple(
            "Intranet  >  Registro",
            "Crear cuenta",
            "Regístrate con tu correo institucional para acceder a la intranet CENAMAD."
        )
        else -> Triple(
            "Intranet",
            "Intranet",
            ""
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(breadcrumb, fontSize = 13.sp, color = PrimaryCyan, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(title, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = DarkText)
        Text(subtitle, fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(top = 4.dp, bottom = 16.dp))

        Crossfade(targetState = currentScreenState, label = "IntranetTransition") { state ->
            when (state) {
                IntranetScreenState.LOGIN -> LoginView(
                    onForgotPasswordClick = { viewModel.cambiarEstado(IntranetScreenState.RECUPERAR_CONTRASEÑA) },
                    onRegisterClick = { viewModel.cambiarEstado(IntranetScreenState.REGISTRO) }
                )
                IntranetScreenState.RECUPERAR_CONTRASEÑA -> RecuperarPasswordView(
                    onBackToLoginClick = { viewModel.cambiarEstado(IntranetScreenState.LOGIN) }
                )
                IntranetScreenState.REGISTRO -> RegistroView(
                    onBackToLoginClick = { viewModel.cambiarEstado(IntranetScreenState.LOGIN) }
                )
                else -> {
                    // Manejo de BIENVENIDA o cualquier otro estado adicional
                    LoginView(
                        onForgotPasswordClick = { viewModel.cambiarEstado(IntranetScreenState.RECUPERAR_CONTRASEÑA) },
                        onRegisterClick = { viewModel.cambiarEstado(IntranetScreenState.REGISTRO) }
                    )
                }
            }
        }
    }
}

@Composable
fun LoginView(onForgotPasswordClick: () -> Unit, onRegisterClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    var showWelcomeDialog by remember { mutableStateOf(false) }

    if (showWelcomeDialog) {
        SimpleModal(
            title = "¡Bienvenido/a!",
            message = "Hola $email, has ingresado correctamente a la Intranet de CENAMAD.",
            onDismiss = { showWelcomeDialog = false }
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        CardContainer {
            Text("Iniciar sesión", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkText)
            Spacer(modifier = Modifier.height(16.dp))

            InputField(label = "Correo electrónico", value = email, placeholder = "nombre@institucion.cl", onValueChange = { email = it })
            Spacer(modifier = Modifier.height(14.dp))
            InputField(label = "Contraseña", value = password, placeholder = "Ingresa tu contraseña", isPassword = true, onValueChange = { password = it })

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = rememberMe, onCheckedChange = { rememberMe = it }, colors = CheckboxDefaults.colors(checkedColor = PrimaryCyan))
                Text("Recuérdame", fontSize = 14.sp, color = Color.Gray)
            }

            Text(
                text = "¿Olvidaste tu contraseña?",
                color = PrimaryCyan,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onForgotPasswordClick() }
            )

            Spacer(modifier = Modifier.height(20.dp))
            PrimaryButton(text = "Iniciar sesión") { if (email.isNotBlank()) showWelcomeDialog = true }

            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text("¿Aún no tienes cuenta? ", fontSize = 14.sp, color = Color.Gray)
                Text("Regístrate", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = PrimaryCyan, modifier = Modifier.clickable { onRegisterClick() })
            }
        }

        InfoCard(icon = Icons.Outlined.Shield, title = "Acceso institucional", description = "Este espacio está destinado a integrantes de la comunidad CENAMAD.")
    }
}