package com.example.movilesprueba.utils

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.movilesprueba.R

fun mostrarNotificacionLeerMasTarde(context: Context, tituloNoticia: String) {
    val channelId = "cenamad_lectura_channel"
    val notificationId = System.currentTimeMillis().toInt() // ID único pa tener varias notificaciones

    // 1. Crear el canal de notificación GOD
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Recordatorios de Lectura"
        val descriptionText = "Notificaciones para leer noticias guardadas de CENAMAD"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    // 2. Diseño de la notificación GOD
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.isologo_cenamad_solo) // Ícono de tu proyecto
        .setContentTitle("Recordatorio: Leer más tarde")
        .setContentText(tituloNoticia)
        .setStyle(NotificationCompat.BigTextStyle().bigText(tituloNoticia)) // Permite ver títulos largos completos
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true)

    // 3. Verificacion de permiso y muestra de la notificacion GOD
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        NotificationManagerCompat.from(context).notify(notificationId, builder.build())
    }
}