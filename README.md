# Proyecto Final de Programación II - Informática Aplicada 2° Año

## Descripción del Proyecto

Este proyecto web, desarrollado como parte del final de una materia, tiene como objetivo gestionar la información de los soldados que realizan el servicio militar. La aplicación incluye un sistema de login y manejo de usuarios con roles diferenciados, donde cada rol tiene acceso a distintas vistas y permisos específicos:

- **Oficial**: Administra todo el sistema.
- **Suboficial**: Administra a los soldados y los servicios.
- **Soldado**: Solo puede consultar información.

## Tecnologías Utilizadas

El sistema está desarrollado en Java utilizando varios frameworks y herramientas para la implementación de la arquitectura MVC, gestión de seguridad, persistencia de datos y más. Las principales dependencias empleadas son:

- **Spring Boot MVC**
- **Spring Security**
- **Spring Data JPA**
- **Thymeleaf**
- **Lombok**
- **MySQL Driver**

Para el servidor web se utilizó el servidor embebido de Spring Boot (Tomcat) y para la gestión de la base de datos MySQL se utilizó XAMPP con PhpMyAdmin.

## Estructura del Sistema

### Entidades Principales

- **Soldado**: Identificado por su código único, nombre, apellidos y graduación. Pertenece a un único cuerpo y compañía durante todo el servicio militar.
- **Cuartel**: Definido por su código, nombre y ubicación. Un cuartel puede alojar varias compañías.
- **Cuerpo del Ejército**: Incluye cuerpos como Infantería, Artillería, Armada, cada uno identificado por un código y denominación.
- **Compañía**: Agrupa soldados de diferentes cuerpos, identificada por su número y actividad principal. Puede estar ubicada en varios cuarteles.
- **Servicio**: Actividades realizadas por los soldados (como correr, limpiar, barrer), identificadas por un código y descripción. Los servicios pueden ser realizados por múltiples soldados, con la fecha de realización siendo significativa.

### Relaciones y Reglas de Negocio

- Un soldado solo puede pertenecer a un cuerpo y una compañía durante todo el servicio.
- Los soldados de una compañía pueden estar distribuidos en diferentes cuarteles.
- Un soldado realiza múltiples servicios a lo largo de su servicio militar, y un servicio puede ser realizado por varios soldados.

## Consideraciones de Seguridad

La seguridad del sistema está gestionada a través de **Spring Security**, asegurando que cada tipo de usuario accede únicamente a las funcionalidades permitidas según su rol.

## Instalación y Ejecución

Para desplegar y ejecutar la aplicación:

1. Clonar el repositorio del proyecto.
2. Configurar una base de datos MySQL utilizando XAMPP y PhpMyAdmin, con el nombre de 'ejercito'.
3. Modificar el archivo `application.properties` con las credenciales de la base de datos (usuario y contraseña).
4. Ejecutar la aplicación con Spring Boot.

```shell
mvn spring-boot:run
```
*El sistema estará disponible en http://localhost:8080.*
