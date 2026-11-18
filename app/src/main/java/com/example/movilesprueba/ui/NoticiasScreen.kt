package com.example.movilesprueba.ui

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movilesprueba.R
import com.example.movilesprueba.model.Noticia
import com.example.movilesprueba.viewmodel.NoticiasViewModel
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.movilesprueba.utils.mostrarNotificacionLeerMasTarde

@Composable
fun NoticiasScreen(viewModel: NoticiasViewModel = viewModel()) {
    val noticias by viewModel.noticias.collectAsState()
    val noticiaSeleccionada by viewModel.noticiaSeleccionada.collectAsState()

    if (noticiaSeleccionada != null) {
        NoticiaDetalleView(
            noticia = noticiaSeleccionada!!,
            onBack = { viewModel.limpiarSeleccion() }
        )
    } else {
        NoticiasListaView(
            noticias = noticias,
            onNoticiaClick = { noticia -> viewModel.seleccionarNoticia(noticia) }
        )
    }
}

@Composable
fun NoticiasListaView(noticias: List<Noticia>, onNoticiaClick: (Noticia) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Noticias Relacionadas",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(noticias) { noticia ->
                NoticiaCard(noticia = noticia, onClick = { onNoticiaClick(noticia) })
            }
        }
    }
}

@Composable
fun NoticiaCard(noticia: Noticia, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = noticia.categoria,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = noticia.fecha,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = noticia.titulo,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = noticia.descripcion,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun NoticiaDetalleView(noticia: Noticia, onBack: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    // LAqui se lanza y se pide el permiso pa mostrar notificaciones
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            mostrarNotificacionLeerMasTarde(context, noticia.titulo)
            Toast.makeText(context, "Recordatorio creado", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Permiso de notificaciones denegado", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Fila superior con el volver y leer mas tarde
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(onClick = onBack) {
                Text("Volver")
            }

            Button(
                onClick = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        // Aqui se verifica si ya tiene el permiso el usuario
                        val tienePermiso = ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.POST_NOTIFICATIONS
                        ) == PackageManager.PERMISSION_GRANTED

                        if (tienePermiso) {
                            mostrarNotificacionLeerMasTarde(context, noticia.titulo)
                            Toast.makeText(context, "Recordatorio creado", Toast.LENGTH_SHORT).show()
                        } else {
                            // Aqui se pide el permiso al usuario
                            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                    } else {

                        mostrarNotificacionLeerMasTarde(context, noticia.titulo)
                        Toast.makeText(context, "Recordatorio creado", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BCE4)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Recordatorio",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("Leer más tarde", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = noticia.categoria,
                fontSize = 14.sp,
                color = Color(0xFF00BCE4),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = noticia.fecha,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Text(
            text = noticia.titulo,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2937)
        )

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.isologo_cenamad_solo),
                    contentDescription = "Imagen de la noticia",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF9FAFB)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "Información de publicación",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "• Autor / Fuente: Equipo de Comunicaciones CENAMAD\n• Categoría oficial: ${noticia.categoria}\n• Tiempo estimado de lectura: 3 min",
                    fontSize = 13.sp,
                    color = Color.DarkGray
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Detalle del artículo",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F2937)
        )

        Text(
            text = noticia.contenidoCompleto,
            fontSize = 15.sp,
            color = Color.DarkGray,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}