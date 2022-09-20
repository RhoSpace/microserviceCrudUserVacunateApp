# Microservicio Usuario

Proyecto Spring Boot con las dependencias:

### Starters para persistencia de datos

* Spring Data JPA
* PostgreSQL Driver

### Starters para web

* Spring Web
* Spring Boot Dev tools

### Starters para documentacion

* SpringFox

### Strarter para seguridad

* Spring Security

### Utilidades

* Lombok

### Descripcion

Microservicio que permite almacenar, crear, editar, listar usuarios con el rol de vacunador y gestionarlos
mediante el usuario administrador

#### Entidad Usario

* User (Clase con el modelo de la base de datos, constructor y getter & setter)
* UserRepository (Permite gestionar las peticiones en base de datos)
* UserController (Permite acceder a los metodos HTML con una url)
    1. Buscar todos los usuarios
    2. Buscar un solo usuarios
    3. Buscar usuarios por rut
    3. Crear un nuevo usuarios
    4. Actualizar un usuarios existente
    5. Borrar un usuarios