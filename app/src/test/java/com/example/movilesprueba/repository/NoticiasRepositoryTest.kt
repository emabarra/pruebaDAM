package com.example.movilesprueba.repository

import com.example.movilesprueba.remote.RetrofitInstance
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class fNoticiasRepositoryTest{
    private lateinit var servidor: MockWebServer
    private lateinit var repository: NoticiasRepository

    @Before
    fun preparar(){
        servidor = MockWebServer()
        servidor.start()
        val api = RetrofitInstance.crearApi(servidor.url("/api:bZ0tEGi6/").toString())
        repository = NoticiasRepositoryImpl(api)
    }
}