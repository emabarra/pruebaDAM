package com.example.movilesprueba.remote

import com.example.movilesprueba.model.Noticia
import retrofit2.http.GET

interface ApiService {
    @GET("https://x8ki-letl-twmt.n7.xano.io/api:bbuWIaQC/noticia/{noticia_id}")
    suspend fun obtenerNoticias(): List<Noticia>
}