package com.example.movilesprueba.ui.intranet

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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