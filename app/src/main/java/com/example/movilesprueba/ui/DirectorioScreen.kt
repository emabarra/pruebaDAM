package com.example.movilesprueba.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movilesprueba.ui.components.TopBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, heightDp = 2200)
@Composable
fun DirectorioScreenPreview() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "CENAMAD",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menú",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = TopBarColor)
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            DirectorioScreen()
        }
    }
}

@Composable
fun DirectorioScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Directorio",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        MiembroItem(
            nombre = "Juan Pablo Pereira",
            cargo = "Presidente del directorio",
            institucion = "CENAMAD"
        )

        MiembroItem(
            nombre = "José Luis Almozán",
            cargo = "Director",
            institucion = "CENAMAD"
        )

        MiembroItem(
            nombre = "Francisca Lorenzini",
            cargo = "Gerente General",
            institucion = "CENAMAD"
        )

        MiembroItem(
            nombre = "Mario Ubilla",
            cargo = "Facultad de Arquitectura, Diseño y Estudios Urbanos",
            institucion = "Universidad Católica"
        )

        MiembroItem(
            nombre = "Christian Ledezma",
            cargo = "Facultad de Ingeniería",
            institucion = "Universidad Católica"
        )

        MiembroItem(
            nombre = "Alejandra Engler",
            cargo = "Facultad de Agronomía y Sistemas Naturales",
            institucion = "Universidad Católica"
        )

        MiembroItem(
            nombre = "Pablo Maturana",
            cargo = "Escuela de Construcción Civil",
            institucion = "Universidad Católica"
        )

        MiembroItem(
            nombre = "Osvaldo Moreno",
            cargo = "Escuela de Arquitectura",
            institucion = "Universidad Católica"
        )

        MiembroItem(
            nombre = "Ramón Molina",
            cargo = "Director Ejecutivo Centro de Innovación",
            institucion = "Universidad Católica"
        )

        MiembroItem(
            nombre = "Francisco Figueroa",
            cargo = "Gerente Comercial",
            institucion = "Maderas Arauco"
        )

        MiembroItem(
            nombre = "Mario Yáñez",
            cargo = "Gerente de Ingeniería e Innovación",
            institucion = "Tecnopanel"
        )

        MiembroItem(
            nombre = "Rodrigo Besnier",
            cargo = "Gerente General",
            institucion = "Arxada Quimetal"
        )

        MiembroItem(
            nombre = "Gina Cerda",
            cargo = "Jefa Desarrollo & Innovación",
            institucion = "CMPC"
        )

        MiembroItem(
            nombre = "Enrique Mc-Manus",
            cargo = "Jefe Desarrollo Construcción en Madera",
            institucion = "Arauco"
        )

        MiembroItem(
            nombre = "Antonio Minte",
            cargo = "Gerente General",
            institucion = "CORMA"
        )

        MiembroItem(
            nombre = "Verónica Loewe",
            cargo = "Investigadora Principal",
            institucion = "INFOR"
        )

        MiembroItem(
            nombre = "Rafael Rubilar",
            cargo = "Investigador Principal",
            institucion = "Universidad de Concepción"
        )

        MiembroItem(
            nombre = "Andrés Fuentes",
            cargo = "Investigador Principal",
            institucion = "Universidad de La Frontera"
        )

        MiembroItem(
            nombre = "Roberto Pizarro",
            cargo = "Investigador Principal",
            institucion = "Universidad de Talca"
        )

        MiembroItem(
            nombre = "Mario Núñez",
            cargo = "Investigador Principal",
            institucion = "Universidad del Bío-Bío"
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
