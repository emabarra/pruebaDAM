package com.example.movilesprueba.repository

import com.example.movilesprueba.model.Noticia
import com.example.movilesprueba.remote.RetrofitInstance

class NoticiasRepository {

    suspend fun obtenerNoticias(): List<Noticia> {
        return try {
            RetrofitInstance.api.obtenerNoticias()
        } catch (e: Exception) {
            android.util.Log.e("NoticiasRepo", "Error al obtener noticias: ${e.message}")
            emptyList() // Devuelve lista vacía en lugar de botar la app
        }

    }
}
