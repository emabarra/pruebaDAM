package com.example.movilesprueba.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

            /*Alianzas*/
            DrawerSimpleItem(
                title = "Alianzas",
                onClick = { onNavigate("alianzas"); onCloseDrawer() }
            )

            /*Trabaja con nosotros*/
            DrawerSimpleItem(
                title = "Trabaja con nosotros",
                onClick = { onNavigate("trabaja_con_nosotros"); onCloseDrawer() }
            )

            /*Intranet*/
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigate("intranet"); onCloseDrawer() }
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(TopBarColor, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Intranet",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Text(
                        text = "Intranet",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1F2937)
                    )
                }
            }
        }
    }
}

/*Componente reutilizable que dibuja una opción individual en forma de tarjta para el menú lateral*/
@Composable
fun DrawerSimpleItem(title: String, onClick: () -> Unit){
                    /*title: texto que se mostra´ra dentro de la tarjeta.
                    * onClick: función lambda que se ejecutará cuando el usuario toque la tarjeta.*/

    /*Encapsula el elemento en una tarjeta blanca*/
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable  { onClick() }
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2937),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun DrawerExpandableItem(
    title: String,
    subItem: List<String>,
    onSubItemClick: (String) -> Unit
){
    var expanded by remember { mutableStateOf(false) }
}