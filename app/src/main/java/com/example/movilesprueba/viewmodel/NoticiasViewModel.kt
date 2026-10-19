package com.example.movilesprueba.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movilesprueba.model.Noticia
import com.example.movilesprueba.repository.NoticiasRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoticiasViewModel (
    private val repository: NoticiasRepository = NoticiasRepository()
) : ViewModel(){

    private val _noticias = MutableStateFlow<List<Noticia>>(emptyList())
    val noticias: StateFlow<List<Noticia>> = _noticias.asStateFlow()

    private val _noticiaSeleccionada = MutableStateFlow<Noticia?>(null)
    val noticiaSeleccionada: StateFlow<Noticia?> = _noticiaSeleccionada.asStateFlow()

    init {
        cargarNoticias()
    }

    private fun cargarNoticias(){
        _noticias.value = repository.getNoticiasRelacionadas()
    }

    fun seleccionarNoticia(noticia: Noticia){
        _noticiaSeleccionada.value = noticia
    }

    fun limpiarSeleccion(){
        _noticiaSeleccionada.value = null
    }
}