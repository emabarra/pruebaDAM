package com.example.movilesprueba.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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