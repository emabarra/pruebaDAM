package com.example.movilesprueba.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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