package com.example.movilesprueba.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class MiembroEquipo(
    val nombre: String,
    val cargo: String,
    val institucion: String
)

@Composable
fun SubdireccionTransferenciaScreen() {
    val scrollState = rememberScrollState()

    val subdirectora = MiembroEquipo(
        nombre = "Javiera De La Barra",
        cargo = "Subdirectora de Transferencia",
        institucion = "Pontificia Universidad Católica"
    )

    val equipo = listOf(
        MiembroEquipo("Fabiana Lorca", "Jefa Área Construcción", "Pontificia Universidad Católica"),
        MiembroEquipo("Diego Maige", "Jefe Área Tecnologías", "Pontificia Universidad Católica"),
        MiembroEquipo("Enric Serra", "Jefe de Proyectos", "Pontificia Universidad Católica"),
        MiembroEquipo("Amanda Apurahal", "Jefa de Proyectos", "Pontificia Universidad Católica"),
        MiembroEquipo("Sofía Fernández", "Profesional de Apoyo", "Pontificia Universidad Católica"),
        MiembroEquipo("Eileen Parmentier", "Profesional de Apoyo", "Pontificia Universidad Católica"),
        MiembroEquipo("Vicente López", "Profesional de Apoyo", "Pontificia Universidad Católica"),
        MiembroEquipo("Josefina Marambio", "Profesional de Apoyo", "Pontificia Universidad Católica")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp) // Margen para evitar que toque los bordes laterales
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos horizontalmente
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Título principal de la sección
        Text(
            text = "Subdirección de Transferencia",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2937),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tarjeta de la Subdirectora
        MiembroCard(miembro = subdirectora)

        Spacer(modifier = Modifier.height(20.dp))

        // Subtítulo Equipo
        Text(
            text = "Equipo de Transferencia",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2937),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Tarjetas del resto del equipo
        equipo.forEach { miembro ->
            MiembroCard(miembro = miembro)
            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun MiembroCard(miembro: MiembroEquipo) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Centra el texto dentro de la tarjeta
        ) {
            Text(
                text = miembro.nombre,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1F2937),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = miembro.cargo,
                fontSize = 14.sp,
                color = Color(0xFF4B5563),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = miembro.institucion,
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}