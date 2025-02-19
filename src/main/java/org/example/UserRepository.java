package org.example;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repositorio para la entidad User, proporciona métodos para realizar operaciones CRUD en MongoDB.
 */
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Encuentra un usuario por su token.
     *
     * @param token el token de seguridad del usuario
     * @return el usuario correspondiente al token
     */
    User findByToken(String token);

    /**
     * Verifica si existe un usuario con el token dado.
     *
     * @param token el token de seguridad del usuario
     * @return true si existe un usuario con el token, false en caso contrario
     */
    Boolean existsByToken(String token);

    /**
     * Verifica si existe un usuario con el correo electrónico y nombre de usuario dados.
     *
     * @param email el correo electrónico del usuario
     * @param user el nombre de usuario del usuario
     * @return true si existe un usuario con el correo electrónico y nombre de usuario, false en caso contrario
     */
    Boolean existsByEmailAndUser(String email, String user);

    /**
     * Encuentra un usuario por su correo electrónico.
     *
     * @param email el correo electrónico del usuario
     * @return el usuario correspondiente al correo electrónico
     */
    User findByEmail(String email);
}