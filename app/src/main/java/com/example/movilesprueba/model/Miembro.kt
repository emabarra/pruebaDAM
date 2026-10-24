package com.example.movilesprueba.model

data class Miembro(
    val id: String,
    val nombre: String,
    val cargo: String,
    val institucion: String,
    val fotoResId: Int? = null
)
