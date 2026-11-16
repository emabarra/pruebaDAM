package com.example.movilesprueba.repository

import com.example.movilesprueba.model.Noticia
import com.example.movilesprueba.remote.RetrofitInstance

class NoticiasRepository {

    suspend fun obtenerNoticias() : List<Noticia>{
        return RetrofitInstance.api.obtenerNoticias()
    }

}
