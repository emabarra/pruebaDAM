package com.example.movilesprueba.ui.intranet

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
//================================================
//1. LOGIN
//================================================
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

// -----------------------------------------------------------
// 2. RECUPERAR CONTRASEÑA
// -----------------------------------------------------------
@Composable
fun RecuperarPasswordView(onBackToLoginClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        SimpleModal(
            title = "Correo enviado",
            message = "Hemos enviado las instrucciones para restablecer tu contraseña al correo:\n\n$email",
            onDismiss = { showDialog = false; onBackToLoginClick() }
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        CardContainer {
            Text("Recibe un enlace de recuperación", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkText)
            Text("Ingresa el correo electrónico asociado a tu cuenta. Te enviaremos un enlace para crear una nueva contraseña.", fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(top = 6.dp, bottom = 16.dp))

            InputField(
                label = "Correo electrónico",
                value = email,
                placeholder = "nombre@institucion.cl",
                onValueChange = { email = it })

            Spacer(modifier = Modifier.height(20.dp))
            PrimaryButton(text = "Enviar enlace de recuperación") { if (email.isNotBlank()) showDialog = true }

            Spacer(modifier = Modifier.height(16.dp))
            BackButton(onClick = onBackToLoginClick)
        }

        InfoCard(icon = Icons.Outlined.Email, title = "Revisa tu correo", description = "Si no encuentras el mensaje, revisa también la carpeta de spam o correo no deseado.")
    }
}

// -----------------------------------------------------------
// 3. REGISTRO
// -----------------------------------------------------------
@Composable
fun RegistroView(onBackToLoginClick: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showRegisterDialog by remember { mutableStateOf(false) }

    if (showRegisterDialog) {
        SimpleModal(
            title = "¡Registro Exitoso!",
            message = "Bienvenido/a $name. Tu cuenta ha sido creada exitosamente.",
            buttonText = "Iniciar sesión",
            onDismiss = { showRegisterDialog = false; onBackToLoginClick() }
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        CardContainer {
            Text("Datos de tu cuenta", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkText)
            Spacer(modifier = Modifier.height(16.dp))

            InputField(
                label = "Nombre completo",
                value = name,
                placeholder = "Ingresa tu nombre y apellido",
                onValueChange = { name = it })
            Spacer(modifier = Modifier.height(14.dp))
            InputField(
                label = "Correo electrónico",
                value = email,
                placeholder = "nombre@institucion.cl",
                onValueChange = { email = it })
            Spacer(modifier = Modifier.height(14.dp))
            InputField(
                label = "Contraseña",
                value = password,
                placeholder = "Crea una contraseña",
                isPassword = true,
                onValueChange = { password = it })
            Text("Usa al menos 8 caracteres.", fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(top = 4.dp, bottom = 14.dp))
           InputField(
                label = "Confirmar contraseña",
                value = confirmPassword,
                placeholder = "Repite tu contraseña",
                isPassword = true,
                onValueChange = { confirmPassword = it })

            Spacer(modifier = Modifier.height(20.dp))
            PrimaryButton(text = "Registrarse") { if (name.isNotBlank() && email.isNotBlank()) showRegisterDialog = true }

            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text("¿Ya tienes una cuenta? ", fontSize = 14.sp, color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(8.dp))
            BackButton(onClick = onBackToLoginClick)
        }

        Text("Registro destinado a integrantes de la comunidad CENAMAD.", fontSize = 13.sp, color = Color.Gray, modifier = Modifier.padding(horizontal = 4.dp))
    }
}

// -----------------------------------------------------------
// COMPONENTES REUTILIZABLES
// -----------------------------------------------------------
@Composable
fun CardContainer(content: @Composable ColumnScope.() -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth(),
        content = { Column(modifier = Modifier.padding(20.dp), content = content) }
    )
}

@Composable
fun InputField(label: String, value: String, placeholder: String, isPassword: Boolean = false, onValueChange: (String) -> Unit) {
    var passwordVisible by remember { mutableStateOf(false) }
    Column {
        Text(label, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = com.tuempresa.cenamad.ui.intranet.DarkText)
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color.LightGray) },
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = if (isPassword) {
                {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(if (passwordVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff, contentDescription = null, tint = Color.Gray)
                    }
                }
            } else null,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(8.dp)
        )
    }
}

@Composable
fun PrimaryButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = PrimaryCyan),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth().height(48.dp)
    ) {
        Text(text, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
    }
}