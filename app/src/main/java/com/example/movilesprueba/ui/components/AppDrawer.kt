package com.example.movilesprueba.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val TopBarColor = Color(0xFF00BCE4)
val DrawerBgColor = Color(0xFFF8F9FA)
val SubItemBgColor = Color(0xFFF0F2F5)

@Composable
fun AppDrawer( /*Construye y gestiona el menú lateral de navegación desplegable*/
    onNavigate: (String) -> Unit, /*Se ejecuta cuando el usuario presuana una opción del menú*/
    onCloseDrawer: () -> Unit /*Se ejecuta inmediatamente despúes de seleccionar una opción para cerrar automáticamente el menú lateral*/
){

}