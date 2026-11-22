package com.example.movilesprueba.model
import com.google.gson.annotations.SerializedName

data class Noticia(
    val id: Int = 0,
    val categoria: String? = "",
    val fecha: String? = "",
    val titulo: String? = "",
    val descripcion: String? = "",
    @SerializedName("contenido_completo") // <-- Verifica en Xano si se llama así o "contenidoCompleto"
    val contenidoCompleto: String? = ""
)