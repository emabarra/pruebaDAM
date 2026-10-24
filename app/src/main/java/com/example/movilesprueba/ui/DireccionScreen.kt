package com.example.movilesprueba.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movilesprueba.ui.components.TopBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DireccionScreenPreview() {
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
            DireccionScreen()
        }
    }
}


@Composable
fun DireccionScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Dirección",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )


        MiembroItem(
            nombre = "José Luis Almozán",
            cargo = "Director CENAMAD",
            institucion = "Pontificia Universidad Católica de Chile"
        )

        MiembroItem(
            nombre = "Francisca Lorenzini",
            cargo = "Gerente General CIM-CENAMAD",
            institucion = "Pontificia Universidad Católica de Chile"
        )

        Spacer(modifier = Modifier.height(8.dp))


        Text(
            text = "Subdirectores",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )


        MiembroItem(
            nombre = "Javiera De La Barra",
            cargo = "Subdirectora de Transferencia",
            institucion = "Pontificia Universidad Católica de Chile"
        )

        MiembroItem(
            nombre = "Raúl Araya",
            cargo = "Subdirector de Investigación",
            institucion = "Pontificia Universidad Católica de Chile"
        )

        MiembroItem(
            nombre = "Romina Rubio",
            cargo = "Subdirectora de Operaciones",
            institucion = "Pontificia Universidad Católica de Chile"
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}


@Composable
fun MiembroItem(
    nombre: String,
    cargo: String,
    institucion: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0E0E0)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Foto de $nombre",
                tint = Color.Gray,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))


        Column {
            Text(
                text = nombre,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = cargo,
                fontSize = 14.sp,
                color = Color.DarkGray
            )
            Text(
                text = institucion,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
