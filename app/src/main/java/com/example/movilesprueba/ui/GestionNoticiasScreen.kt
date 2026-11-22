package com.example.movilesprueba.ui

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movilesprueba.model.Noticia
import com.example.movilesprueba.ui.components.TopBarColor
import com.example.movilesprueba.viewmodel.GestionNoticiasViewModel
import com.example.movilesprueba.viewmodel.IntranetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GestionNoticiasScreenPreview() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "CENAMAD",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
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
        Box(modifier = Modifier.padding(paddingValues)) {
            GestionNoticiasContent(
                isLoggedIn = true,
                onIrALogin = {},
                viewModel = viewModel()
            )
        }
    }
}

@Composable
fun GestionNoticiasScreen(
    intranetViewModel: IntranetViewModel = viewModel(),
    gestionViewModel: GestionNoticiasViewModel = viewModel(),
    onNavigateToIntranet: () -> Unit = {}
) {
    val isLoggedIn by intranetViewModel.isLoggedIn.collectAsState()

    GestionNoticiasContent(
        isLoggedIn = isLoggedIn,
        onIrALogin = onNavigateToIntranet,
        viewModel = gestionViewModel
    )
}

@Composable
fun GestionNoticiasContent(
    isLoggedIn: Boolean,
    onIrALogin: () -> Unit,
    viewModel: GestionNoticiasViewModel
) {
    val scrollState = rememberScrollState()
    val noticias by viewModel.noticias.collectAsState()
    val noticiaEdicion by viewModel.noticiaEdicion.collectAsState()
    val mostrarFormulario by viewModel.mostrarFormulario.collectAsState()

    if (!isLoggedIn) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Acceso Denegado",
                tint = TopBarColor,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Acceso Restringido",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Debes iniciar sesión en la Intranet para crear, editar o eliminar tus noticias.",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onIrALogin,
                colors = ButtonDefaults.buttonColors(containerColor = TopBarColor),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Ir a Iniciar Sesión", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Gestión de Noticias",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Button(
                    onClick = { viewModel.abrirCrear() },
                    colors = ButtonDefaults.buttonColors(containerColor = TopBarColor),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Nueva", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }

            if (noticias.isEmpty()) {
                Text(
                    text = "No has publicado ninguna noticia aún.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 16.dp)
                )
            } else {
                noticias.forEach { noticia ->
                    NoticiaGestionItem(
                        noticia = noticia,
                        onEditar = { viewModel.abrirEditar(noticia) },
                        onEliminar = { viewModel.eliminarNoticia(noticia.id) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        if (mostrarFormulario) {
            FormularioNoticiaDialog(
                noticiaExistente = noticiaEdicion,
                onGuardar = { titulo, cat, desc, cont ->
                    viewModel.guardarNoticia(titulo, cat, desc, cont)
                },
                onCerrar = { viewModel.cerrarFormulario() }
            )
        }
    }
}

@Composable
fun NoticiaGestionItem(
    noticia: Noticia,
    onEditar: () -> Unit,
    onEliminar: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFE8F7FC), shape = RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = noticia.categoria,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = TopBarColor
                    )
                }

                Row {
                    IconButton(onClick = onEditar) {
                        Icon(Icons.Default.Edit, contentDescription = "Editar", tint = Color.Gray)
                    }
                    IconButton(onClick = onEliminar) {
                        Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color(0xFFE53935))
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = noticia.titulo,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = noticia.descripcion,
                fontSize = 14.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Fecha: ${noticia.fecha}",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun FormularioNoticiaDialog(
    noticiaExistente: Noticia?,
    onGuardar: (String, String, String, String) -> Unit,
    onCerrar: () -> Unit
) {
    var titulo by remember { mutableStateOf(noticiaExistente?.titulo ?: "") }
    var categoria by remember { mutableStateOf(noticiaExistente?.categoria ?: "General") }
    var descripcion by remember { mutableStateOf(noticiaExistente?.descripcion ?: "") }
    var contenido by remember { mutableStateOf(noticiaExistente?.contenidoCompleto ?: "") }

    AlertDialog(
        onDismissRequest = onCerrar,
        title = {
            Text(
                text = if (noticiaExistente == null) "Nueva Noticia" else "Editar Noticia",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = categoria,
                    onValueChange = { categoria = it },
                    label = { Text("Categoría") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción corta") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = contenido,
                    onValueChange = { contenido = it },
                    label = { Text("Contenido completo") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onGuardar(titulo, categoria, descripcion, contenido) },
                enabled = titulo.isNotBlank() && descripcion.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = TopBarColor)
            ) {
                Text("Guardar", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onCerrar) {
                Text("Cancelar", color = Color.Gray)
            }
        }
    )
}
