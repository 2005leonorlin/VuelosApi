Descripción

Esta API permite la gestión de vuelos y usuarios en una base de datos MongoDB. Se pueden realizar operaciones CRUD sobre los vuelos y gestionar la autenticación de usuarios mediante tokens de seguridad.

Recursos

Usuario (User)

Representa a un usuario del sistema.

Atributos:

_id: Identificador único del usuario.

user: Nombre de usuario.

email: Dirección de correo electrónico.

token: Token de autenticación.

Vuelo (Vuelo)

Representa un vuelo dentro del sistema.

Atributos:

id: Identificador único del vuelo.

numeroVuelo: Número de vuelo.

aerolinea: Nombre de la aerolínea.

origenCod: Código del aeropuerto de origen.

origenNombre: Nombre del aeropuerto de origen.

origenCiudad: Ciudad del aeropuerto de origen.

origenPais: País del aeropuerto de origen.

destinoCod: Código del aeropuerto de destino.

destinoNombre: Nombre del aeropuerto de destino.

destinoCiudad: Ciudad del aeropuerto de destino.

destinoPais: País del aeropuerto de destino.

horaSalida: Hora de salida del vuelo.

horaLlegada: Hora de llegada del vuelo.

estado: Estado del vuelo.

Endpoints

Autenticación

POST /login

Iniciar sesión con usuario y email.

Ejemplo de solicitud:

{
  "user": "juan123",
  "email": "juan@example.com"
}

Ejemplo de respuesta:

{
  "_id": "656abc123",
  "user": "juan123",
  "email": "juan@example.com",
  "token": "abcde12345"
}

GET /login/exit

Cerrar sesión.

Vuelos

GET /api/vuelos

Obtener todos los vuelos.

Ejemplo de respuesta:

[
  {
    "id": "1",
    "numeroVuelo": "IB1234",
    "aerolinea": "Iberia",
    "origenCiudad": "Madrid",
    "destinoCiudad": "Barcelona",
    "horaSalida": "10:00",
    "horaLlegada": "11:30",
    "estado": "En horario"
  }
]

GET /api/vuelos/{id}

Obtener un vuelo por ID.

Ejemplo de respuesta:

{
  "id": "1",
  "numeroVuelo": "IB1234",
  "aerolinea": "Iberia",
  "origenCiudad": "Madrid",
  "destinoCiudad": "Barcelona",
  "horaSalida": "10:00",
  "horaLlegada": "11:30",
  "estado": "En horario"
}

POST /api/

Crear un nuevo vuelo.

Ejemplo de solicitud:

{
  "id": "2",
  "numeroVuelo": "AF5678",
  "aerolinea": "Air France",
  "origenCiudad": "París",
  "destinoCiudad": "Madrid",
  "horaSalida": "14:00",
  "horaLlegada": "16:00",
  "estado": "Retrasado"
}

Respuesta esperada:

{
  "id": "2",
  "numeroVuelo": "AF5678",
  "aerolinea": "Air France",
  "origenCiudad": "París",
  "destinoCiudad": "Madrid",
  "horaSalida": "14:00",
  "horaLlegada": "16:00",
  "estado": "Retrasado"
}

DELETE /api/vuelos/{id}

Eliminar un vuelo por ID (requiere token de seguridad).

Ejemplo de solicitud:

DELETE /api/vuelos/2?token=abcde12345

Respuesta esperada:

{
  "status": 204
}

Seguridad

El acceso a la eliminación de vuelos requiere autenticación mediante un token. Este token se obtiene al iniciar sesión y se verifica antes de ejecutar la acción.

Tecnologías utilizadas

Java con Spring Boot

MongoDB

Lombok

Spring Data MongoDB

