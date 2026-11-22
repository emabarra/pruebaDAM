package com.example.movilesprueba.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.movilesprueba.model.Noticia
import com.example.movilesprueba.repository.NoticiasRepository
import java.io.IOException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

sealed interface NoticiasUiState {
    data object Cargando : NoticiasUiState
    data class Exito(val noticias: List<Noticia>) : NoticiasUiState
    data object Error : NoticiasUiState
}

class NoticiasViewModel(
    private val repository: NoticiasRepository
) : ViewModel() {

    private val _estado = MutableStateFlow<NoticiasUiState>(NoticiasUiState.Cargando)
    val estado: StateFlow<NoticiasUiState> = _estado.asStateFlow()

    // Conservamos tu lógica de selección de noticia
    private val _noticiaSeleccionada = MutableStateFlow<Noticia?>(null)
    val noticiaSeleccionada: StateFlow<Noticia?> = _noticiaSeleccionada.asStateFlow()

    private var carga: Job? = null

    init {
        cargarNoticias()
    }

    fun cargarNoticias() {
        if (carga?.isActive == true) return
        _estado.value = NoticiasUiState.Cargando
        carga = viewModelScope.launch {
            try {
                _estado.value = NoticiasUiState.Exito(repository.obtenerNoticias())
            } catch (e: IOException) {
                _estado.value = NoticiasUiState.Error
            } catch (e: HttpException) {
                _estado.value = NoticiasUiState.Error
            }
        }
    }

    fun seleccionarNoticia(noticia: Noticia) {
        _noticiaSeleccionada.value = noticia
    }

    fun limpiarSeleccion() {
        _noticiaSeleccionada.value = null
    }

    class Factory(private val repository: NoticiasRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass.isAssignableFrom(NoticiasViewModel::class.java))
            @Suppress("UNCHECKED_CAST")
            return NoticiasViewModel(repository) as T
        }
    }
}