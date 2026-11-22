package com.example.movilesprueba.model

data class Noticia (
    val id: Int,
    val categoria: String,
    val fecha: String,
    val titulo: String,
    val descripcion: String,
    val contenidoCompleto: String
)