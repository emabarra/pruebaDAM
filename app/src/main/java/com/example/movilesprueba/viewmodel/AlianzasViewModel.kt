package com.example.movilesprueba.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movilesprueba.model.Alianza
import com.example.movilesprueba.repository.AlianzasRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AlianzasViewModel(
    private val repository: AlianzasRepository = AlianzasRepository()
) : ViewModel() {

    private val _asociadosAcademia = MutableStateFlow<List<Alianza>>(emptyList())
    val asociadosAcademia: StateFlow<List<Alianza>> = _asociadosAcademia.asStateFlow()

    private val _asociadosSectorPublico = MutableStateFlow<List<Alianza>>(emptyList())
    val asociadosSectorPublico: StateFlow<List<Alianza>> = _asociadosSectorPublico.asStateFlow()

    private val _aportantesSectorPublico = MutableStateFlow<List<Alianza>>(emptyList())
    val aportantesSectorPublico: StateFlow<List<Alianza>> = _aportantesSectorPublico.asStateFlow()

    private val _aportantesSectorPrivado = MutableStateFlow<List<Alianza>>(emptyList())
    val aportantesSectorPrivado: StateFlow<List<Alianza>> = _aportantesSectorPrivado.asStateFlow()

    init {
        cargarAlianzas()
    }

    private fun cargarAlianzas() {
        _asociadosAcademia.value = repository.getAsociadosAcademia()
        _asociadosSectorPublico.value = repository.getAsociadosSectorPublico()
        _aportantesSectorPublico.value = repository.getAportantesSectorPublico()
        _aportantesSectorPrivado.value = repository.getAportantesSectorPrivado()
    }
}
