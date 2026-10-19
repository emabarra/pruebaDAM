package com.example.movilesprueba.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.movilesprueba.model.Noticia
import com.example.movilesprueba.viewmodel.NoticiasViewModel

@Composable
fun NoticiasScreen(viewModel: NoticiasViewModel = viewModel()){
    val noticias by viewModel.noticias.collectAsState()
    val noticiaSeleccionada by viewModel.noticiaSeleccionada.collectAsState()

    if (noticiaSeleccionada != null){
        NoticiaDetalleView(
            noticia = noticiaSeleccionada!!,
            onBack = { viewModel.limpiarSeleccion() }
        )
    }else{
        NoticiasListaView(
            noticias = noticias,
            onNoticiaClick = { noticia -> viewModel.seleccionarNoticia(noticia) }
        )
    }
}

@Composable
fun NoticiasListaView(noticias: List<Noticia>, onNoticiaClick: (Noticia) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Noticias Relacionadas",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(noticias){ noticia ->
                NoticiaCard(noticia = noticia, onClick = { onNoticiaClick(noticia) })

            }
        }
    }
}

@Composable
fun NoticiaCard(noticia: Noticia, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = noticia.categoria,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = noticia.fecha,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = noticia.titulo,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = noticia.descripcion,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun NoticiaDetalleView(noticia: Noticia, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = onBack, modifier = Modifier.padding(bottom = 12.dp)) {
            Text("Volver")
        }
        Text(
            text = noticia.categoria,
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = noticia.fecha,
            fontSize = 12.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = noticia.titulo,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = noticia.contenidoCompleto,
            fontSize = 16.sp,
            color = Color.DarkGray
        )
    }
}