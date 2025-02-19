package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Clase de servicio para manejar operaciones relacionadas con la seguridad.
 */
@Service
public class SecurityService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Valida el token de seguridad proporcionado.
     *
     * @param token el token de seguridad a validar
     * @return true si el token es válido, false en caso contrario
     */
    public Boolean requestValidation(String token){
        return userRepository.existsByToken(token);
    }

    /**
     * Inicia sesión de un usuario basado en su nombre de usuario y correo electrónico.
     *
     * @param user el nombre de usuario del usuario
     * @param email el correo electrónico del usuario
     * @return un Optional que contiene el Usuario si se encuentra, o un Optional vacío si no se encuentra
     */
    public Optional<User> login(String user, String email){
        if(userRepository.existsByEmailAndUser(email, user)){
            return Optional.ofNullable(userRepository.findByEmail(email));
        }
        return Optional.empty();
    }
}