package com.example.movilesprueba.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TrabajaConNosotrosViewModel : ViewModel() {

    private val _nombre = MutableStateFlow("")
    val nombre: StateFlow<String> = _nombre.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _mensaje = MutableStateFlow("")
    val mensaje: StateFlow<String> = _mensaje.asStateFlow()

    private val _formularioEnviado = MutableStateFlow(false)
    val formularioEnviado: StateFlow<Boolean> = _formularioEnviado.asStateFlow()

    fun onNombreChange(nuevoNombre: String) {
        _nombre.value = nuevoNombre
    }

    fun onEmailChange(nuevoEmail: String) {
        _email.value = nuevoEmail
    }

    fun onMensajeChange(nuevoMensaje: String) {
        _mensaje.value = nuevoMensaje
    }

    fun enviarFormulario() {
        if (_nombre.value.isNotBlank() && _email.value.isNotBlank()) {
            _formularioEnviado.value = true
        }
    }

    fun reiniciarFormulario() {
        _nombre.value = ""
        _email.value = ""
        _mensaje.value = ""
        _formularioEnviado.value = false
    }
}
