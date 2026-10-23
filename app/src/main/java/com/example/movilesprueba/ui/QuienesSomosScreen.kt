package com.example.movilesprueba.ui

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.movilesprueba.R
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movilesprueba.ui.intranet.InfoCard

@Composable
fun QuienesSomosScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Qué es CENAMAD",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2937)
        )

        // Tarjeta del Logo (Reemplazar con la imagen real de tu drawable)
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.isologo_cenamad_solo),
                    contentDescription = "Logo CENAMAD",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp), // Ajusta la altura según tu diseño
                    contentScale = ContentScale.Fit
                )
            }
        }

        Text(
            text = "Centro Nacional de Excelencia para la Industria de la Madera",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = "CENAMAD es un Centro Basal de excelencia creado a fines de 2021, a partir de la adjudicación del Concurso de apoyo a Centros de Excelencia de la Agencia Nacional de Investigación y Desarrollo (ANID).",
            fontSize = 14.sp,
            color = Color.DarkGray
        )

        Text(
            text = "Nació con una visión integral de la cadena de valor de la madera y una hoja de ruta con tres ejes: sustentabilidad y productividad forestal, bioproductos de valor agregado y construcción con madera.",
            fontSize = 14.sp,
            color = Color.DarkGray
        )

        Text(
            text = "Misión y visión",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2937),
            modifier = Modifier.padding(top = 8.dp)
        )

        InfoCard(
            icon = Icons.Outlined.Public,
            title = "¿Cuál es nuestra misión?",
            description = "Habilitar la construcción en altura y masificar la construcción en mediana altura en madera en Chile, a través del desarrollo sustentable de los bosques y productos de valor agregado en madera"
        )

        InfoCard(
            icon = Icons.Outlined.Build,
            title = "¿Cuál es nuestra visión?",
            description = "Convertir a la madera en el principal motor de la bioeconomía y la sustentabilidad de mediano y largo plazo en Chile."
        )

        InfoCard(
            icon = Icons.Outlined.Place, // Icono representativo (reemplazar por target)
            title = "Nuestra Meta",
            description = "Generar todas las condiciones ambientales, sociales y técnico-económicas, para la construcción de edificios en altura de 15 o más pisos en madera."
        )

        Text(
            text = "Objetivos",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2937),
            modifier = Modifier.padding(top = 8.dp)
        )

        ObjetivoCard(text = "Excelencia científica a través de investigaciones que impulsen la productividad, publicaciones, patentes e integración de nuevos talentos.")
        ObjetivoCard(text = "Impacto tanto el sector público, privado y la sociedad, a través de la transferencia de conocimiento y el desarrollo de proyectos innovadores.")

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun InfoCard(icon: ImageVector, title: String, description: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(Color(0xFF5C78B7), shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = description,
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}

@Composable
fun ObjetivoCard(text: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Color(0xFFE0F7FA), shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color(0xFF00BCE4),
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}