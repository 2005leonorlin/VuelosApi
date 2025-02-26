package org.example;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Controlador web para manejar solicitudes relacionadas con vuelos.
 * Este controlador permite mostrar, crear y filtrar vuelos por diferentes criterios como aerolínea y ciudad de origen.
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
     * @param session la sesión HTTP actual
     * @return el nombre de la vista a renderizar
     */
    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        var vuelos = vueloRepository.findAll();
        model.addAttribute("titulo", "Listado de vuelos");
        model.addAttribute("vuelos", vuelos);
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("usuario", user);
        }
        return "index";
    }

    /**
     * Maneja las solicitudes GET a la URL "/{id}" para mostrar detalles de un vuelo específico.
     *
     * @param model el modelo para agregar atributos
     * @param id el ID del vuelo a buscar
     * @return el nombre de la vista a renderizar
     */
    @GetMapping("/{id}")
    public String single(Model model, @PathVariable String id) {
        System.out.println("Buscando vuelo con ID: " + id); // Depuración
        var vuelo = vueloRepository.findById(id);
        if (vuelo.isEmpty()) {
            System.out.println("Vuelo no encontrado"); // Depuración
            return "404";
        } else {
            System.out.println("Vuelo encontrado: " + vuelo.get().getNumeroVuelo()); // Depuración
            model.addAttribute("vuelo", vuelo.get());
            return "single";
        }
    }

    /**
     * Maneja las solicitudes GET a la URL "/new" para mostrar el formulario de creación de un nuevo vuelo.
     * Redirige a la página de login si el usuario no está autenticado.
     *
     * @param session la sesión HTTP actual
     * @return el nombre de la vista a renderizar
     */
    @GetMapping("/new")
    public String createNew(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "new";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * Maneja las solicitudes POST a la URL "/new" para crear un nuevo vuelo.
     * Redirige a la página de login si el usuario no está autenticado.
     *
     * @param session la sesión HTTP actual
     * @param vuelo el objeto Vuelo con los datos a crear
     * @return el nombre de la vista a renderizar
     */
    @PostMapping("/new")
    public String create(HttpSession session, @ModelAttribute Vuelo vuelo) {
        if (session.getAttribute("user") != null) {
            vueloRepository.save(vuelo);
            return "redirect:/web/";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * Maneja las solicitudes GET a la URL "/aerolinea/{aerolinea}" para mostrar los vuelos de una aerolínea específica.
     *
     * @param session la sesión HTTP actual
     * @param aerolinea la aerolínea de los vuelos a mostrar
     * @param model el modelo para agregar atributos
     * @return el nombre de la vista a renderizar
     */
    @GetMapping("/aerolinea/{aerolinea}")
    public String vuelosPorAerolinea(HttpSession session, @PathVariable String aerolinea, Model model) {
        List<Vuelo> vuelos = vueloRepository.findByAerolinea(aerolinea);

        model.addAttribute("aerolinea", aerolinea);
        model.addAttribute("vuelos", vuelos);


        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("usuario", user);
        }

        return "vuelosPorAerolinea";
    }

    /**
     * Maneja las solicitudes GET a la URL "/aerolineas" para mostrar la lista de aerolíneas disponibles.
     *
     * @param session la sesión HTTP actual
     * @param model el modelo para agregar atributos
     * @return el nombre de la vista a renderizar
     */
    @GetMapping("/aerolineas")
    public String listarAerolineas(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("usuario", user);


            List<Vuelo> todosVuelos = vueloRepository.findAll();


            Set<String> aerolineasUnicas = todosVuelos.stream()
                    .map(Vuelo::getAerolinea)
                    .filter(aerolinea -> aerolinea != null && !aerolinea.isEmpty())
                    .collect(Collectors.toSet());

            model.addAttribute("aerolineas", aerolineasUnicas);
            return "aerolineas";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * Maneja las solicitudes GET a la URL "/origen/ciudades" para listar las ciudades de origen disponibles.
     *
     * @param session la sesión HTTP actual
     * @param model el modelo para agregar atributos
     * @return el nombre de la vista a renderizar
     */
    @GetMapping("/origen/ciudades")
    public String listarCiudadesOrigen(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        List<Vuelo> todosVuelos = vueloRepository.findAll();
        Set<String> ciudadesUnicas = todosVuelos.stream()
                .map(Vuelo::getOrigenciudad)
                .filter(ciudad -> ciudad != null && !ciudad.isEmpty())
                .collect(Collectors.toSet());

        model.addAttribute("ciudades", ciudadesUnicas);
        User user = (User) session.getAttribute("user");
        model.addAttribute("usuario", user);

        return "ciudadesOrigen";
    }

    /**
     * Maneja las solicitudes GET a la URL "/origen/ciudad/{ciudad}" para mostrar los vuelos que salen de una ciudad específica.
     *
     * @param session la sesión HTTP actual
     * @param ciudad la ciudad de origen de los vuelos a mostrar
     * @param model el modelo para agregar atributos
     * @return el nombre de la vista a renderizar
     */
    @GetMapping("/origen/ciudad/{ciudad}")
    public String vuelosPorCiudad(HttpSession session, @PathVariable String ciudad, Model model) {

        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }


        List<Vuelo> vuelos = vueloRepository.findByOrigenciudad(ciudad);


        model.addAttribute("vuelos", vuelos);
        model.addAttribute("ciudad", ciudad);


        User user = (User) session.getAttribute("user");
        model.addAttribute("usuario", user);

        return "vuelosPorCiudad";
    }
}