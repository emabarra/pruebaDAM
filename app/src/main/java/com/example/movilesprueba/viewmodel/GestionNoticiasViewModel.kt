package com.example.movilesprueba.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movilesprueba.model.Noticia
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GestionNoticiasViewModel : ViewModel() {

    private val _noticias = MutableStateFlow<List<Noticia>>(
        listOf(
            Noticia(
                id = 101,
                categoria = "Investigación",
                fecha = "20/10/2026",
                titulo = "Mi primera publicación sobre madera sustentable",
                descripcion = "Resumen del trabajo de investigación realizado en el laboratorio CENAMAD.",
                contenidoCompleto = "Detalle completo del trabajo de investigación sobre sustentabilidad..."
            ),
            Noticia(
                id = 102,
                categoria = "Transferencia",
                fecha = "15/10/2026",
                titulo = "Avances en proyectos de construcción en madera",
                descripcion = "Informe sobre los avances alcanzados en los proyectos regionales.",
                contenidoCompleto = "Contenido extensivo sobre los avances de construcción..."
            )
        )
    )
    val noticias: StateFlow<List<Noticia>> = _noticias.asStateFlow()

    private val _noticiaEdicion = MutableStateFlow<Noticia?>(null)
    val noticiaEdicion: StateFlow<Noticia?> = _noticiaEdicion.asStateFlow()

    private val _mostrarFormulario = MutableStateFlow(false)
    val mostrarFormulario: StateFlow<Boolean> = _mostrarFormulario.asStateFlow()

    fun abrirCrear() {
        _noticiaEdicion.value = null
        _mostrarFormulario.value = true
    }

    fun abrirEditar(noticia: Noticia) {
        _noticiaEdicion.value = noticia
        _mostrarFormulario.value = true
    }

    fun cerrarFormulario() {
        _noticiaEdicion.value = null
        _mostrarFormulario.value = false
    }

    fun guardarNoticia(titulo: String, categoria: String, descripcion: String, contenido: String) {
        val actual = _noticias.value.toMutableList()
        val edicion = _noticiaEdicion.value

        if (edicion != null) {
            val index = actual.indexOfFirst { it.id == edicion.id }
            if (index != -1) {
                actual[index] = edicion.copy(
                    titulo = titulo,
                    categoria = categoria,
                    descripcion = descripcion,
                    contenidoCompleto = contenido
                )
            }
        } else {
            val nuevoId = (actual.maxOfOrNull { it.id } ?: 0) + 1
            val nueva = Noticia(
                id = nuevoId,
                categoria = categoria,
                fecha = "Hoy",
                titulo = titulo,
                descripcion = descripcion,
                contenidoCompleto = contenido
            )
            actual.add(0, nueva)
        }

        _noticias.value = actual
        cerrarFormulario()
    }

    fun eliminarNoticia(id: Int) {
        _noticias.value = _noticias.value.filterNot { it.id == id }
    }
}
