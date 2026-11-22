package com.example.movilesprueba.remote

import com.example.movilesprueba.model.Noticia
import retrofit2.http.GET

interface ApiService {
    @GET("noticia")
    suspend fun obtenerNoticias(): List<Noticia>
}