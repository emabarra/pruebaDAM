package com.example.movilesprueba.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.movilesprueba.data.preferences.UserPreferences
import com.example.movilesprueba.ui.intranet.IntranetScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class IntranetViewModel(application: Application) : AndroidViewModel(application) {

    private val userPreferences = UserPreferences(application)

    private val _screenState = MutableStateFlow(IntranetScreenState.LOGIN)
    val screenState: StateFlow<IntranetScreenState> = _screenState.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    private val _userEmail = MutableStateFlow("")
    val userEmail: StateFlow<String> = _userEmail.asStateFlow()

    init {
        // Al iniciar el ViewModel, observamos si existe una sesión guardada
        viewModelScope.launch {
            userPreferences.isLoggedIn.collect { loggedIn ->
                _isLoggedIn.value = loggedIn
            }
        }
        viewModelScope.launch {
            userPreferences.userEmail.collect { email ->
                _userEmail.value = email
            }
        }
    }

    fun cambiarEstado(nuevoEstado: IntranetScreenState) {
        _screenState.value = nuevoEstado
    }

    // Iniciar sesión y guardar en DataStore
    fun iniciarSesion(email: String) {
        viewModelScope.launch {
            userPreferences.saveSession(email)
        }
    }

    // Cerrar sesión y limpiar DataStore
    fun cerrarSesion() {
        viewModelScope.launch {
            userPreferences.clearSession()
            _screenState.value = IntranetScreenState.LOGIN
        }
    }
}