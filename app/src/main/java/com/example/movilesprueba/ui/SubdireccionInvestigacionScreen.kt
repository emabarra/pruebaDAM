package com.example.movilesprueba.ui

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

@Composable
fun SubdireccionInvestigacionScreen() {
    val scrollState = rememberScrollState()

    val subdirector = MiembroEquipo(
        nombre = "Raúl Araya",
        cargo = "Subdirector de Investigación",
        institucion = "Pontificia Universidad Católica"
    )

    val equipo = listOf(
        MiembroEquipo("Sebastián Berwart", "Jefe de Área de Estructuras", "Pontificia Universidad Católica de Chile"),
        MiembroEquipo("Fernando Véliz", "Jefe de Proyectos", "Pontificia Universidad Católica de Chile"),
        MiembroEquipo("Martín Beltrán", "Profesional de Apoyo", "Pontificia Universidad Católica de Chile"),
        MiembroEquipo("Arantxa Bilbao", "Profesional de Apoyo", "Pontificia Universidad Católica de Chile"),
        MiembroEquipo("Jorge Pérez", "Profesional de Apoyo", "Pontificia Universidad Católica de Chile")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Título principal
        Text(
            text = "Subdirección de Investigación",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2937),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tarjeta del Subdirector
        MiembroCard(miembro = subdirector)

        Spacer(modifier = Modifier.height(20.dp))

        // Subtítulo Equipo
        Text(
            text = "Equipo de Investigación",
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