package com.example.movilesprueba.repository

import com.example.movilesprueba.model.Noticia

class NoticiasRepository {

    fun getNoticiasRelacionadas(): List<Noticia>{
        return listOf(
            Noticia(
                id = "1",
                categoria = "Ejemplo de noticia",
                fecha = "18/10/2026",
                titulo = "Actualización de servicios en CENAMAD",
                descripcion = "Se informa sobre nuevas iniciativas y mejoraas en los servicios disponibles para la comunidad.",
                contenidoCompleto = "Contenido detallado de la actualización de servicios en CENAMAD..."
            ),
            Noticia(
                id = "2",
                categoria = "Ejemplo de noticia",
                fecha = "17/10/2026",
                titulo = "Evento comunitario en CENAMAD",
                descripcion = "Se invita a participar de una jornada de actividades y talleres organizados por la institución.",
                contenidoCompleto = "Contenido detallado sobre el evento comunitario..."
            )
        )
    }

}