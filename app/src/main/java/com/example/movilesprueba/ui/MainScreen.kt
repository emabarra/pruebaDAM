package com.example.movilesprueba.ui

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope

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
}