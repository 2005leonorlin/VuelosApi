package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal para la aplicación Spring Boot de Vuelos API.
 */
@SpringBootApplication
public class VuelosApiApplication {

    /**
     * El punto de entrada de la aplicación Vuelos API.
     *
     * @param args argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(VuelosApiApplication.class, args);
    }

}