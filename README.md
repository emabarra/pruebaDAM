<div align="center">

  # 📱 App Mobile CENAMAD

  **Aplicación móvil oficial para el Centro Nacional de Excelencia para la Industria de la Madera**

  [![Kotlin](https://img.shields.io/badge/Kotlin-1.9%2B-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
  [![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-Material3-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
  [![Android Studio](https://img.shields.io/badge/Android_Studio-Hedgehog%2B-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white)](https://developer.android.com/studio)
  [![Xano](https://img.shields.io/badge/Backend-Xano_API-00BCE4?style=for-the-badge&logo=databricks&logoColor=white)](https://www.xano.com/)
  [![Platform](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://www.android.com/)
  [![License](https://img.shields.io/badge/License-Academic-blue?style=for-the-badge)](#)

</div>

---

## 📖 Sobre el Proyecto

Esta aplicación móvil nativa para **Android** fue desarrollada para **CENAMAD** (*Centro Nacional de Excelencia para la Industria de la Madera*).  
Su objetivo principal es difundir noticias, avances científicos y proyectos de innovación en torno a la industria maderera en Chile, además de ofrecer transparencia institucional sobre su visión, equipos de trabajo, indicadores de logros, alianzas y oportunidades laborales en los sectores público y privado.

---

## ✨ Características Principales

- 📰 **Noticias y Proyectos en Tiempo Real:** Visualización de publicaciones actualizadas desde una API externa, con vista de detalle completo y opción de **recordatorios ("Leer más tarde")** mediante notificaciones locales.
- 🔐 **Intranet y Gestión de Noticias:** Módulo privado con autenticación para crear, editar y eliminar publicaciones dinámicamente.
- 🏛️ **Identidad Institucional:** Secciones dedicadas a *Qué es CENAMAD*, líneas de investigación, indicadores de impacto, alianzas estratégicas y *Trabaja con nosotros*.
- 👥 **Equipos de Trabajo:** Directorio transparente de las distintas subdirecciones y áreas que conforman el centro.
- 📱 **Interfaz Moderna:** Diseño responsivo construido con **Jetpack Compose** y **Material Design 3**, con navegación fluida mediante menú lateral (`ModalNavigationDrawer`).

---

## 🗄️ Base de Datos Externa e Integración API (Xano)

La aplicación utiliza **Xano** como plataforma *Backend-as-a-Service (BaaS)* y base de datos externa en la nube para gestionar la persistencia y consulta de datos de forma dinámica:

- **Consumo de API REST:** Implementado mediante **Retrofit 2** y **Gson** para la serialización de objetos JSON hacia modelos de datos en Kotlin (`Noticia`).
- **Arquitectura Reactiva (MVVM):** Uso de `ViewModel`, `StateFlow` y corrutinas de Kotlin (`viewModelScope`) para manejar estados de carga (`Cargando`, `Exito`, `Error`) de forma asíncrona sin bloquear la interfaz de usuario.
- **Endpoint Principal:**
  - `GET /noticia` — Obtiene el listado actualizado de noticias y artículos del centro desde el servidor de Xano.

> **Nota:** Para que la aplicación cargue los datos correctamente en dispositivos físicos o emuladores, se requiere conexión a Internet activa (`android.permission.INTERNET`).

---

## ⚒️ Tecnologías y Herramientas

- **Lenguaje de Programación:** [Kotlin](https://kotlinlang.org/)
- **Interfaz de Usuario (UI):** [Jetpack Compose](https://developer.android.com/jetpack/compose) & Material 3
- **Arquitectura:** MVVM (*Model-View-ViewModel*) + Repository Pattern
- **Red y Base de Datos Externa:** [Xano](https://www.xano.com/) (Backend / DB) & [Retrofit](https://square.github.io/retrofit/)
- **Entorno de Desarrollo (IDE):** [Android Studio](https://developer.android.com/studio)
- **Control de Versiones:** Git & GitHub

---

## 🚀 Instalación y Configuración Local

Para clonar y ejecutar esta aplicación en tu entorno local, sigue estos pasos:

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/emabarra/pruebaDAM.git](https://github.com/emabarra/pruebaDAM.git)
2. **Abrir el proyecto:**

  Abre Android Studio, y abre la carpeta del proyecto clonado.

3. **Sincronizar dependencias:**

  Deja que Gradle descargue las dependencias necesarias.

4. **Ejecutar App:**

  Conecta un dispositivo físico vía USB (con depuración USB activa) o inicia un emulador Android (AVD) y presiona

```bash

Run (Shift + F10)

```
---
## 👥 Equipo

| Integrantes |
|---|
| Emanuel Barra |
| Bastian Morales |
| Benjamin Vargas |
---

<div align="center">

**Duoc UC — Desarrollo de Aplicaciones Móviles — 2026**

</div>
