package com.example.movilesprueba.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng



data class EjeEstrategico(
    val numero: String,
    val titulo: String,
    val color: Color,
    val descripcion: String,
    val iniciativas: List<String>
)

@Composable
fun NuestrasLineasScreen() {
    val scrollState = rememberScrollState()

    val ejes = listOf(
        EjeEstrategico(
            numero = "01",
            titulo = "Sustentabilidad y Productividad Forestal",
            color = Color(0xFF2E7D32), // Verde
            descripcion = "El eje de Sustentabilidad y Productividad Forestal abarca desde el manejo forestal y la silvicultura hasta el desarrollo forestal, garantizando el uso y conservación de los recursos forestales de la zona. A través de una gestión eficiente y sostenible, promueve la producción de bienes y servicios forestales, para mejorar la productividad de la industria maderera.",
            iniciativas = listOf(
                "Mejorar la sustentabilidad hídrica, productividad y captura de carbono de bosques y plantaciones forestales en un escenario de cambio climático.",
                "Mejorar la diversidad, calidad y disponibilidad de madera.",
                "Evaluar costos y beneficios socioeconómicos ambientales de bosques o plantaciones en el corto, mediano o largo plazo.",
                "Promoción de la resiliencia y adaptación de bosques y plantaciones a escenarios con mayor frecuencia de incendios forestales y de mayor incertidumbre."
            )
        ),
        EjeEstrategico(
            numero = "02",
            titulo = "Bioproductos de Valor Agregado",
            color = Color(0xFFE65100), // Naranja
            descripcion = "Centrado en la tala, el transporte y la transformación de la materia prima, el eje de Bioproductos de Valor Agregado se enfoca en revalorizar las partes del árbol que tradicionalmente se consideran de bajo valor comercial y utilizadas principalmente como combustible (aserrín, virutas, cortezas, entre otros), transformándolas en recursos valiosos para la industria, con ello permitiendo el desarrollo económico y sustentabilidad necesaria del sector.",
            iniciativas = listOf(
                "El desarrollo de productos sustentables para la mejora del desempeño en la construcción en madera.",
                "La revalorización de los subproductos industriales para el ámbito de la construcción en madera."
            )
        ),
        EjeEstrategico(
            numero = "03",
            titulo = "Construcción en Madera",
            color = Color(0xFF3F51B5), // Azul / Violeta
            descripcion = "El eje de Construcción con Madera, se centra en el desarrollo y promoción de productos de ingeniería en madera (Mass Timber) e investigaciones, acciones y proyectos, buscando aumentar y masificar el uso de la madera estructural, proporcionando soluciones innovadoras y sustentables para la industria de la construcción con madera.",
            iniciativas = listOf(
                "Desarrollo y optimización de sistemas constructivos mass timber, híbridos y marco plataforma.",
                "Análisis de los impactos socioambientales y socioeconómicos de la construcción en madera.",
                "Mitigación del riesgo de las construcciones en madera.",
                "Implementación de un modelo de gestión colaborativo integrado y digitalización para la construcción industrializada.",
                "Eficiencia energética, confort y salud de las construcciones en madera.",
                "Edificaciones demostrativas y plataformas tecnológicas para construcción."
            )
        ),
        EjeEstrategico(
            numero = "04",
            titulo = "Herramientas Integradoras de Apoyo al Conocimiento",
            color = Color(0xFF7E57C2), // Morado
            descripcion = "El área de Herramientas Integradoras de Apoyo al Conocimiento agrupa a las áreas de Comunicaciones y Extensión, Finanzas, Recursos Humanos, Formación, Administración y Género. Al ser transversal es responsable de apoyar y dar cuenta de cada acción promovida para contribuir al posicionamiento de los diversos equipos de trabajo en la realización de sus proyectos, al facilitar el avance y reconocimiento de cada iniciativa que desarrollan a través de recursos e iniciativas, herramientas y línea apoyo.",
            iniciativas = listOf(
                "Divulgación científica y tecnológica.",
                "Atracción de público especializado.",
                "Formación de capital humano técnico, profesional y científico.",
                "Inclusión social y equidad de género en la cadena del valor.",
                "Vinculación e integración internacional."
            )
        ),

    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        // Encabezado
        Text(
            text = "Hoja de Ruta CENAMAD",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2937)
        )

        Text(
            text = "CENAMAD ha proporcionado soluciones integrales y completas para aprovechar al máximo los recursos de la madera y promover su uso sostenible. Es por esto que para lograr la excelencia científica se divide en cuatro ejes: Sustentabilidad y Productividad Forestal, Bioproductos de Valor Agregado, Construcción con Madera y Herramientas Integradoras de Apoyo al Conocimiento.",
            fontSize = 14.sp,
            color = Color.DarkGray
        )

        // Renderizado de los 4 ejes
        ejes.forEach { eje ->
            EjeEstrategicoSeccion(eje = eje)
        }

        MapaSedeCenamad()

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun EjeEstrategicoSeccion(eje: EjeEstrategico) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Título del Eje con el Número Grande al lado
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = eje.numero,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = eje.color
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = eje.titulo,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = eje.color,
                modifier = Modifier.weight(1f)
            )
        }

        // Descripción general del eje
        Text(
            text = eje.descripcion,
            fontSize = 14.sp,
            color = Color.DarkGray
        )

        // Subtítulo Iniciativas
        Text(
            text = "Sus iniciativas estratégicas son:",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2937),
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
        )

        // Lista de Tarjetas para cada Iniciativa
        eje.iniciativas.forEach { iniciativa ->
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "•",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        modifier = Modifier.padding(end = 12.dp)
                    )
                    Text(
                        text = iniciativa,
                        fontSize = 14.sp,
                        color = Color(0xFF4B5563)
                    )
                }
            }
        }
    }
}

@Composable
fun MapaSedeCenamad() {
    // Coordenadas aproximadas del Centro de Innovación UC Anacleto Angelini (Campus San Joaquín)
    val ubicacionCenamad = LatLng(-33.49936500787212, -70.61654033901539)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(ubicacionCenamad, 16f)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = "¿Quieres saber dónde está nuestra sede?. Acá abajo puedes verlo",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2937),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Centro de Innovación en Madera UC - Edificio Anacleto Angelini.\nAv. Vicuña Mackenna 4860, Piso 7, Macul, Santiago.",
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = ubicacionCenamad),
                    title = "CENAMAD",
                    snippet = "Centro Nacional de Excelencia para la Industria de la Madera"
                )
            }
        }
    }
}