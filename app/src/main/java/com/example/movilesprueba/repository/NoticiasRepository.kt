package com.example.movilesprueba.repository

import com.example.movilesprueba.model.Noticia
import com.example.movilesprueba.remote.RetrofitInstance
import com.example.movilesprueba.remote.ApiService

interface NoticiasRepository {
    suspend fun obtenerNoticias(): List<Noticia>
}

class NoticiasRepositoryImpl(
    private val api: ApiService
) : NoticiasRepository {
    override suspend fun obtenerNoticias(): List<Noticia> = api.obtenerNoticias()
}
