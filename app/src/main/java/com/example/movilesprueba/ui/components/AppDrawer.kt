package com.example.movilesprueba.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val TopBarColor = Color(0xFF00BCE4)
val DrawerBgColor = Color(0xFFF8F9FA)
val SubItemBgColor = Color(0xFFF0F2F5)

@Composable
fun AppDrawer( /*Construye y gestiona el menú lateral de navegación desplegable*/
    onNavigate: (String) -> Unit, /*Se ejecuta cuando el usuario presuana una opción del menú*/
    onCloseDrawer: () -> Unit /*Se ejecuta inmediatamente despúes de seleccionar una opción para cerrar automáticamente el menú lateral*/
){
    ModalDrawerSheet(/*Define el panel visual interiror del menú lateral desplegable*/
        drawerContainerColor = DrawerBgColor,
        modifier = Modifier.width(320.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            /*Tarjeta de Noticias*/
            DrawerSimpleItem(
                title = "Noticias",
                onClick = { onNavigate("noticias"); onCloseDrawer() }
            )

            /*Indicadores de Productividad*/
            DrawerSimpleItem(
                title = "Indicadores de Productividad",
                onClick = { onNavigate("indicadores"); onCloseDrawer }
            )

            /*Quienes somos*/
            DrawerExpandableItem(
                title = "Quienes somos",
                subItems = listOf("Qué es CENAMAD", "Nuestras líneas"),
                onSubItemClick = { subItem ->
                    onNavigate("quienes_somos/$subItem")
                    onCloseDrawer()
                }
            )

            /*Equipos*/
            DrawerExpandableItem(
                title = "Equipos",
                subItems = listOf(
                    "Dirección",
                    "Directorio",
                    "Subdirección de Investigación",
                    "Subdirección de Transferencia",
                    "Subdirección de Operaciones"
                ),
                onSubItemClick = { subItem ->
                    onNavigate("equipos/$subItem")
                    onCloseDrawer()
                }
            )
        }
    }
}