package com.example.movilesprueba.model

data class Alianza(
    val id: String,
    val titulo: String,
    val subtitulo: String? = null,
    val tipo: String,
    val categoria: String
)
