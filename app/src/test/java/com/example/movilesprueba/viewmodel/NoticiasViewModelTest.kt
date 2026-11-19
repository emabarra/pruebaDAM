package com.example.movilesprueba.viewmodel

import com.example.movilesprueba.model.Noticia
import com.example.movilesprueba.repository.NoticiasRepository
import java.io.IOException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class NoticiasViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun preparar(){
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun limpiar(){
        Dispatchers.resetMain()
    }

    @Test
    fun `una lista vacia se publica como exito`() = runTest {
        println("INICIO: comprobar una lista vacia.")
        println("PREPARACION: el repositorio falso no devolvera noticias.")
        val viewModel = NoticiasViewModel(FakeRepository{emptyList()})

        println("ACCION: ejecutar la carga.")
        advanceUntilIdle()
        println("COMPROBACION: una lista vacia debe ser un resultado exitoso.")
        assertEquals(NoticiasUiState.Exito(emptyList()), viewModel.estado.value)
        println("CORRECTO: se publico una lista vacia sin error.")
    }

    private class FakeRepository(
        private val respuesta: suspend () -> List<Noticia>
    ): NoticiasRepository{
        override suspend fun obtenerNoticias(): List<Noticia> = respuesta()
    }
}