# StreetShoot

StreetShoot es una aplicación Android desarrollada como proyecto académico,
basada en la gamificación de zonas urbanas mediante la captura de jugadores
en distintos Streetspots.

El proyecto sigue una metodología Scrum y utiliza una arquitectura cliente-servidor.

## Requisitos del entorno

Para ejecutar el proyecto es necesario disponer de:

- Android Studio
- JDK 17 o superior
- MySQL Server y MySQL Workbench
- XAMPP (Apache + PHP)
- Git
- Emulador Android o dispositivo físico

## Instalación del proyecto

1. Clonar el repositorio:
   git clone <URL_DEL_REPOSITORIO>

2. Abrir el proyecto Android en Android Studio.

3. Sincronizar Gradle y comprobar que el proyecto compila correctamente.

## Configuración de la base de datos

1. Abrir MySQL Workbench y conectarse al servidor local.
2. Crear la base de datos ejecutando el archivo:
   database/schema.sql
3. Insertar datos de prueba ejecutando:
   database/seed.sql

La base de datos utilizada se llama "streetshoot".

## Ejecución del backend

1. Instalar y abrir XAMPP.
2. Activar el módulo Apache.
3. Copiar la carpeta backend del repositorio dentro de:
   xampp/htdocs/

4. Acceder desde el navegador a:
   http://localhost/backend/api/ping.php

Si la respuesta es un JSON con "ok: true", el backend está funcionando correctamente.

## Ejecución de la aplicación Android

1. Abrir el proyecto en Android Studio.
2. Iniciar un emulador Android o conectar un dispositivo físico.
3. Ejecutar la aplicación con el botón Run.

La aplicación se iniciará en la pantalla de Login.

## Notas importantes

- El proyecto se encuentra en fase de desarrollo.
- El backend se ejecuta en entorno local.
- Para el emulador Android, las peticiones al backend utilizan la IP 10.0.2.2.
