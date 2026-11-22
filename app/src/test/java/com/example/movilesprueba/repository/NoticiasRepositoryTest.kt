package com.example.movilesprueba.repository

import com.example.movilesprueba.model.Noticia
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

    @After
    fun limpiar(){
        servidor.shutdown()
    }

    @Test
    fun `consulta el endpoint y convierte la respuesta`() = runTest{
        println("INICIO: comprobar la solicitud y la conversion del JSON.")
        println("PREPARACION: el servidor local simulado respondera con noticia 1 Actualizacion de servicios en CENAMAD.")
        servidor.enqueue(MockResponse().setBody(
            """[{"id":3,"created_at":1795093994795,"categoria":"Ejemplo de noticia","fecha":"Nov 19, 2026","titulo":"Nuevos recursos disponibles","descripcion":"Se han agregado materiales y recursos para la consulta pública en la sección de biblioteca.","contenido_completo":"Se han incorporado nuevos documentos técnicos, guías de diseño en madera y bases de datos especializadas en la plataforma de consulta pública de CENAMAD. Estos recursos están disponibles para investigadores, estudiantes y profesionales del sector interesados en profundizar en tecnologías constructivas avanzadas y gestión forestal sostenible. Para acceder a los materiales completos, puedes visitar la sección de recursos o contactar con el área de documentación."}]"""
        ))

        println("ACCION: el repositorio llama al servidor lcal, no al servicio real.")
        val resultado = repository.obtenerNoticias()

        println("COMPROBACION: el JSON debe convertirse en una noticia y usar GET /noticia.")
        assertEquals(listOf(Noticia(3,"Ejemplo de noticia","Nov 19, 2026", "Nuevos recursos disponibles", "Se han agregado materiales y recursos para la consulta pública en la sección de biblioteca.", "Se han agregado materiales y recursos para la consulta pública en la sección de biblioteca.\",\"contenido_completo\":\"Se han incorporado nuevos documentos técnicos, guías de diseño en madera y bases de datos especializadas en la plataforma de consulta pública de CENAMAD. Estos recursos están disponibles para investigadores, estudiantes y profesionales del sector interesados en profundizar en tecnologías constructivas avanzadas y gestión forestal sostenible. Para acceder a los materiales completos, puedes visitar la sección de recursos o contactar con el área de documentación.")), resultado)
        val solicitud = servidor.takeRequest()
        assertEquals("GET", solicitud.method)
        assertEquals("/api:bZ0tEGi6/noticia", solicitud.path)
        println("CORRECTO: la solicitud y la noticia son los esperados.")

    }

    @Test
    fun `propaga errores http`() = runTest {
        println("INICIO: comprobar la propagacion deerrores HTTP.")
        println("PREPARACION: el servidor local simulado respondera con HTTP 500.")
        servidor.enqueue(MockResponse().setResponseCode(500))

        println("ACCION: solicitar personajes al servidor local.")
        try {
            repository.obtenerNoticias()
            throw AssertionError("Se esperaba un HttpException")
        }catch (error: HttpException){
            println("COMPROBACION: el error recibido debe tener codigo 500.")
            assertEquals(500, error.code())
        }

        println("CORRECTO: el repositorio propago el error HTTP 500.")
    }

}