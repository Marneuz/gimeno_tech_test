# GimenoTechTest

Esta es una aplicación de demostración para gestionar un directorio de empleados, que incluye funciones de registro, inicio de sesión y visualización de detalles de los empleados.

## Descripción

La aplicación permite a los usuarios registrarse, iniciar sesión, ver un directorio de empleados y ver detalles individuales de cada empleado. Está construida con [Jetpack Compose](https://developer.android.com/jetpack/compose) para la UI y utiliza [Hilt](https://dagger.dev/hilt/) para la inyección de dependencias.

## Características

- Registro de usuario
- Inicio de sesión
- Visualización de un directorio de empleados
- Visualización de detalles individuales de empleados
- Búsqueda de empleados
- Cierre de sesión

## Uso

### Registro

1. Abre la aplicación
2. Navega a la pantalla de registro
3. Introduce tu email y contraseña
4. Haz clic en "Registrarme"

### Inicio de Sesión

1. Abre la aplicación
2. Introduce tu email y contraseña
3. Haz clic en "Acceder"

### Directorio de Empleados

1. Inicia sesión en la aplicación
2. Navega a la pantalla del directorio de empleados
3. Busca empleados por nombre o puesto de trabajo
4. Haz clic en un empleado para ver sus detalles

### Detalles del Empleado

1. Haz clic en un empleado en la pantalla del directorio
2. Ve los detalles del empleado, incluyendo nombre, posición, teléfono y email
3. Usa los iconos para llamar al empleado o enviarle un email

### Cerrar Sesión

1. Haz clic en el menú desplegable en la esquina superior derecha de la pantalla del directorio
2. Selecciona "Cerrar Sesión"

## Estructura del Proyecto

- `data/`: Contiene la implementación de repositorios y el manejo de datos
- `di/`: Contiene los módulos de Hilt para la inyección de dependencias
- `domain/`: Contiene los modelos de datos y los casos de uso (use cases)
- `ui/`: Contiene las vistas y la lógica de UI (ViewModels)
  - `views/`: Contiene las pantallas principales de la aplicación
  - `common/`: Contiene componentes comunes reutilizables en diferentes vistas

## Dependencias

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Hilt](https://dagger.dev/hilt/)
- [Coil](https://coil-kt.github.io/coil/) para la carga de imágenes
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) para el almacenamiento de datos

## Clean Architecture

Toda la aplicación está hecha basándose en principios de clean architecture, utilizando Jetpack Compose, Hilt, Coil y DataStore.

## Funcionalidades

- Al iniciar la aplicación, se hace una comprobación de si existe o no un usuario. En caso de existir, accede directamente al directorio; en caso contrario, entra al login, desde el cual puedes acceder a crear cuenta para registrar el usuario.
- Una vez registrado, puedes acceder desde el login al directorio. En el directorio hay un mock de 100 empleados, en los que se muestra una tarjeta con nombre, puesto e imagen. En caso de no tener imagen, se mostrarán las iniciales en su lugar.
- En la parte superior del directorio se puede buscar usuarios tanto por nombre como por puesto de trabajo. También hay un botón de cerrar sesión, el cual elimina el usuario (en una aplicación real no debería eliminarlo del todo, pero se agregó esta funcionalidad para manejo del registro en lugar de una simple navegación).
- Al hacer clic en cualquier empleado se accede a una vista detalle, en la que se mostrará imagen, nombre, puesto, teléfono y email. También tiene dos iconos que permiten de forma automática realizar una llamada o enviar un email al usuario.
