package com.example.movilesprueba.ui

import android.R.attr.contentDescription
import android.R.attr.tint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movilesprueba.ui.components.AppDrawer
import com.example.movilesprueba.ui.components.TopBarColor
import com.example.movilesprueba.ui.intranet.IntranetScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    /*Guarda el estado del menú lateral (si está abierto o cerrado).
    * Inicia cerrado por defecto*/
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    /*Crea un ciclo de vida para ejecutar corrutinas asincronicas, necesarias para animar la
    * apertura y cierre del menú*/
    val scope = rememberCoroutineScope()

    /*"noticias" es la vista principal inicial de la app*/
    var currentRoute by remember { mutableStateOf("noticias") }

    /*Encapsula toda la estructura para permitir que el menú se deslice sobre la pantalla*/
    ModalNavigationDrawer(
        drawerState = drawerState,

        /*Dibuja el componente AppDrawer en el panel lateral*/
        drawerContent = {
            AppDrawer(
                /*Cuando el usuario presiona una opción del menú, actualiza la variable currentRoute
                * con el identificador de la nueva pantalla*/
                onNavigate = { route ->
                    currentRoute = route
                },

                /*Ejecuta scope.launch para cerrar suavemente el menú lateral con animación*/
                onCloseDrawer = {
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {
        Scaffold(
            /*Dibuja la barra superior de color turquesa*/
            topBar = {
                TopAppBar(
                    title = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text = "CENAMAD",
                                color = Color.White,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {scope.launch { drawerState.open() }}){
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = TopBarColor)
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)){
                when(currentRoute){
                    "noticias" -> NoticiasScreen()
                    "intranet" -> IntranetScreen()
                    "quienes_somos/Qué es CENAMAD" -> QuienesSomosScreen()
                    // "quienes_somos/Nuestras líneas" -> NuestrasLineasScreen() - Ruta Preeliminar
                    else -> {
                        //Marcador para las demás secciones a medida que se creen
                        Box(
                            modifier = Modifier.padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Sección: $currentRoute",
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}