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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movilesprueba.ui.components.TopBarColor
import com.example.movilesprueba.viewmodel.AlianzasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, heightDp = 2200)
@Composable
fun AlianzasScreenPreview() {
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
            AlianzasScreen()
        }
    }
}

@Composable
fun AlianzasScreen(
    viewModel: AlianzasViewModel = viewModel()
) {
    val scrollState = rememberScrollState()

    val asociadosAcademia by viewModel.asociadosAcademia.collectAsState()
    val asociadosPublico by viewModel.asociadosSectorPublico.collectAsState()
    val aportantesPublico by viewModel.aportantesSectorPublico.collectAsState()
    val aportantesPrivado by viewModel.aportantesSectorPrivado.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Inicio",
                fontSize = 13.sp,
                color = Color.Gray
            )
            Text(
                text = "  >  ",
                fontSize = 13.sp,
                color = Color.Gray
            )
            Text(
                text = "Alianzas",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32)
            )
        }

        Text(
            text = "Asociados",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Box(
            modifier = Modifier
                .background(Color(0xFFE8F5E9), shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(
                text = "${asociadosAcademia.size + asociadosPublico.size} instituciones",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2E7D32)
            )
        }

        Text(
            text = "Academia",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = "${asociadosAcademia.size} instituciones",
            fontSize = 13.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        asociadosAcademia.forEach { alianza ->
            AlianzaCard(
                titulo = alianza.titulo,
                subtitulo = alianza.subtitulo
            )
        }

        Text(
            text = "Sector Público",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = "${asociadosPublico.size} institución",
            fontSize = 13.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        asociadosPublico.forEach { alianza ->
            AlianzaCard(
                titulo = alianza.titulo,
                subtitulo = alianza.subtitulo
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Aportantes",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Box(
            modifier = Modifier
                .background(Color(0xFFE8F5E9), shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(
                text = "${aportantesPublico.size + aportantesPrivado.size} entidades",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2E7D32)
            )
        }

        Text(
            text = "Sector Público",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = "${aportantesPublico.size} institución",
            fontSize = 13.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        aportantesPublico.forEach { alianza ->
            AlianzaCard(
                titulo = alianza.titulo,
                subtitulo = alianza.subtitulo
            )
        }

        Text(
            text = "Sector privado",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = "${aportantesPrivado.size} empresas y organizaciones",
            fontSize = 13.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        aportantesPrivado.forEach { alianza ->
            AlianzaCard(
                titulo = alianza.titulo,
                subtitulo = alianza.subtitulo
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF7EE)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Alianzas",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32)
                )
                Text(
                    text = "Entidades identificadas por nombre. Logotipos no incorporados.",
                    fontSize = 13.sp,
                    color = Color.DarkGray
                )
                Text(
                    text = "${asociadosAcademia.size + asociadosPublico.size} asociados · ${aportantesPublico.size + aportantesPrivado.size} aportantes",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun AlianzaCard(
    titulo: String,
    subtitulo: String? = null
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = titulo,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            if (subtitulo != null) {
                Text(
                    text = subtitulo,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .width(28.dp)
                    .height(3.dp)
                    .background(Color(0xFF2E7D32), shape = RoundedCornerShape(2.dp))
            )
        }
    }
}
