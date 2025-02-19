package org.example;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador web para manejar solicitudes relacionadas con vuelos.
 */
@Controller
@RequestMapping("/web")
public class WebController {

    @Autowired
    VueloRepository vueloRepository;

    /**
     * Maneja las solicitudes GET a la URL raíz ("/") y devuelve la vista de índice con una lista de vuelos.
     *
     * @param model el modelo para agregar atributos
     * @return el nombre de la vista a renderizar
     */
    @GetMapping("/")
    public String index(Model model) {
        var vuelos = vueloRepository.findAll();
        model.addAttribute("titulo", "Listado de vuelos");
        model.addAttribute("vuelos", vuelos);
        return "index";
    }
}