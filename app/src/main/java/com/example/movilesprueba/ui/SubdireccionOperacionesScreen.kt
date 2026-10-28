package com.example.movilesprueba.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun SubdireccionOperacionesScreen() {
    val scrollState = rememberScrollState()

    val subdirectora = MiembroEquipo(
        nombre = "Romina Rubio",
        cargo = "Subdirectora de Operaciones",
        institucion = "Pontificia Universidad Católica"
    )

    val equipoAdministrativo = listOf(
        MiembroEquipo("Carolina Galdames", "Coordinadora Subdirección de Operaciones", "Pontificia Universidad Católica"),
        MiembroEquipo("María Consuelo Hernández", "Asistente Administrativa", "Pontificia Universidad Católica"),
        MiembroEquipo("Victor Villagra", "Encargado Contable", "Pontificia Universidad Católica"),
        MiembroEquipo("Daniela Hermosilla", "Analista Contable", "Pontificia Universidad Católica")
    )

    val equipoComunicaciones = listOf(
        MiembroEquipo("Jessica Dirinot", "Encargada de Comunicaciones", "Pontificia Universidad Católica"),
        MiembroEquipo("Tomás Franz", "Encargado de Contenidos", "Pontificia Universidad Católica"),
        MiembroEquipo("Vanessa Naranjo", "Diseñadora Gráfica", "Pontificia Universidad Católica"),
        MiembroEquipo("Jesús Moller", "Comunicador Audiovisual", "Pontificia Universidad Católica")
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
            text = "Subdirección de Operaciones",
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

        // Sección Equipo Administrativo
        Text(
            text = "Equipo Administrativo",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2937),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        equipoAdministrativo.forEach { miembro ->
            MiembroCard(miembro = miembro)
            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sección Equipo de Comunicaciones
        Text(
            text = "Equipo de Comunicaciones",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2937),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        equipoComunicaciones.forEach { miembro ->
            MiembroCard(miembro = miembro)
            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}