package com.example.movilesprueba.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movilesprueba.model.Miembro
import com.example.movilesprueba.repository.EquiposRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EquiposViewModel(
    private val repository: EquiposRepository = EquiposRepository()
) : ViewModel() {

    private val _direccion = MutableStateFlow<List<Miembro>>(emptyList())
    val direccion: StateFlow<List<Miembro>> = _direccion.asStateFlow()

    private val _subdirectores = MutableStateFlow<List<Miembro>>(emptyList())
    val subdirectores: StateFlow<List<Miembro>> = _subdirectores.asStateFlow()

    private val _directorio = MutableStateFlow<List<Miembro>>(emptyList())
    val directorio: StateFlow<List<Miembro>> = _directorio.asStateFlow()

    init {
        cargarEquipos()
    }

    private fun cargarEquipos() {
        _direccion.value = repository.getDireccion()
        _subdirectores.value = repository.getSubdirectores()
        _directorio.value = repository.getDirectorio()
    }
}
