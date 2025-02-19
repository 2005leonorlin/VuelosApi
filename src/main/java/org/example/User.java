package org.example;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representa una entidad de Usuario almacenada en la colección "Users" en MongoDB.
 */
@Document(collection = "Users")
@Data
public class User {
    /**
     * El identificador único del usuario.
     */
    @Id
    private String _id;

    /**
     * El nombre de usuario del usuario.
     */
    private String user;

    /**
     * La dirección de correo electrónico del usuario.
     */
    private String email;

    /**
     * El token de seguridad asociado con el usuario.
     */
    private String token;
}