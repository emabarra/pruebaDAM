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
                descripcion = "Se informa sobre nuevas iniciativas y mejoras en los servicios disponibles para la comunidad.",
                contenidoCompleto = "CENAMAD ha implementado una serie de mejoras y nuevas iniciativas estratégicas orientadas a optimizar la atención, el acceso a recursos tecnológicos y la colaboración en proyectos de investigación. Estas acciones buscan fortalecer el vínculo con la comunidad académica e industrial, promoviendo estándares de excelencia en la bioeconomía y la construcción sustentable.\n\nDurante las próximas semanas se habilitarán nuevos canales de comunicación y soporte técnico para todos los equipos de trabajo, facilitando la gestión de proyectos y la transferencia de conocimientos."
            ),
            Noticia(
                id = "2",
                categoria = "Ejemplo de noticia",
                fecha = "17/10/2026",
                titulo = "Evento comunitario en CENAMAD",
                descripcion = "Se invita a participar de una jornada de actividades y talleres organizados por la institución.",
                contenidoCompleto = "El centro invita a toda su comunidad a ser parte de la próxima jornada abierta de talleres, charlas magistrales y mesas de trabajo colaborativo. Este evento tiene como objetivo principal compartir los avances más recientes en sustentabilidad forestal y bioproductos de valor agregado.\n\nLa actividad se llevará a cabo en las dependencias principales y contará con transmisión online para los participantes de regiones, fomentando la participación descentralizada."
            ),
            Noticia(
                id = "3",
                categoria = "Ejemplo de noticia",
                fecha = "10/10/2026",
                titulo = "Nuevos recursos disponibles",
                descripcion = "Se han agregado materiales y recursos para la consulta pública en la sección de biblioteca.",
                contenidoCompleto = "Se han incorporado nuevos documentos técnicos, guías de diseño en madera y bases de datos especializadas en la plataforma de consulta pública de CENAMAD. Estos recursos están disponibles para investigadores, estudiantes y profesionales del sector interesados en profundizar en tecnologías constructivas avanzadas y gestión forestal sostenible.\n\nPara acceder a los materiales completos, puedes visitar la sección de recursos o contactar con el área de documentación."
            )
        )
    }

}
