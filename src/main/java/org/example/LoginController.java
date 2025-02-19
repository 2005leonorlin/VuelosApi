package org.example;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller para manejar las operaciones relacionadas con el inicio de sesión.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * Servicio encargado de manejar la lógica de seguridad.
     */
    @Autowired
    SecurityService securityService;

    /**
     * Maneja las solicitudes GET para mostrar la página de inicio de sesión.
     *
     * @param model objeto Model para agregar atributos a la vista.
     * @return el nombre de la plantilla de la vista de inicio de sesión.
     */
    @GetMapping
    public String login(Model model) {
        return "login";
    }

    /**
     * Procesa las solicitudes POST para intentar iniciar sesión.
     *
     * @param session la sesión HTTP para almacenar información del usuario autenticado.
     * @param model objeto Model para agregar atributos a la vista en caso de error.
     * @param login objeto User que contiene las credenciales ingresadas por el usuario.
     * @return redirige a la página principal si el inicio de sesión es exitoso,
     *         de lo contrario regresa a la página de inicio de sesión con un mensaje de error.
     */
    @PostMapping
    public String processLogin(HttpSession session, Model model, @ModelAttribute User login) {
        var result = securityService.login(login.getUser(), login.getEmail());
        if(result.isPresent()){
            session.setAttribute("user", result.get());
            return "redirect:/web/";
        } else {
            model.addAttribute("error", " Incorrecta la contraseña");
            return "login";
        }
    }

    /**
     * Maneja las solicitudes GET para cerrar sesión.
     *
     * @param session la sesión HTTP que será invalidada.
     * @return redirige a la página principal después de cerrar sesión.
     */
    @GetMapping("/exit")
    public String exit(HttpSession session) {
        session.invalidate();
        return "redirect:/web/";
    }
}

