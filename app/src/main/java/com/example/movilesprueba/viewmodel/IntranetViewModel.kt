package com.example.movilesprueba.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movilesprueba.ui.intranet.IntranetScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class IntranetViewModel : ViewModel() {
    private val _screenState = MutableStateFlow(IntranetScreenState.LOGIN)
    val screenState: StateFlow<IntranetScreenState> = _screenState.asStateFlow()

    fun cambiarEstado(nuevoEstado : IntranetScreenState){
        _screenState.value = nuevoEstado
    }
}