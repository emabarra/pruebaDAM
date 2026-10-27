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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Campaign
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Handshake
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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

data class IndicadorData(
    val icon: ImageVector,
    val cifra: String,
    val titulo: String
)

@Composable
fun IndicadoresScreen() {
    val scrollState = rememberScrollState()

    val indicadores = listOf(
        IndicadorData(Icons.Outlined.Book, "224", "Publicaciones"),
        IndicadorData(Icons.Outlined.Mic, "265", "Ponencias nacionales e internacionales"),
        IndicadorData(Icons.Outlined.Handshake, "86", "Redes de colaboración"),
        IndicadorData(Icons.Outlined.WorkspacePremium, "13", "Postdoctorados"),
        IndicadorData(Icons.Outlined.School, "9", "Doctorados finalizados"),
        IndicadorData(Icons.Outlined.StarBorder, "67", "Magíster finalizados"),
        IndicadorData(Icons.Outlined.ReceiptLong, "15", "Programas de postgrado"),
        IndicadorData(Icons.Outlined.Edit, "20", "Patentes"),
        IndicadorData(Icons.Outlined.LocationCity, "53", "Proyectos con la industria"),
        IndicadorData(Icons.Outlined.Campaign, "276", "Actividades de outreach y difusión")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "Indicadores de Productividad",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2937),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Cabecera de la tabla
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Spacer(modifier = Modifier.width(56.dp)) // Espacio del icono
                    Text(
                        text = "Cifra",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        modifier = Modifier.width(60.dp)
                    )
                    Text(
                        text = "Indicador",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }

                HorizontalDivider(color = Color(0xFFF0F2F5), thickness = 1.dp)

                // Filas de indicadores
                indicadores.forEachIndexed { index, indicador ->
                    IndicadorFila(indicador)
                    if (index < indicadores.size - 1) {
                        HorizontalDivider(color = Color(0xFFF0F2F5), thickness = 1.dp)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun IndicadorFila(indicador: IndicadorData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFE0F7FA), shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = indicador.icon,
                contentDescription = null,
                tint = Color(0xFF00BCE4),
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = indicador.cifra,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2937),
            modifier = Modifier.width(60.dp)
        )

        Text(
            text = indicador.titulo,
            fontSize = 14.sp,
            color = Color(0xFF4B5563),
            modifier = Modifier.weight(1f)
        )
    }
}