package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar Vuelos.
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired VueloRepository vueloRepository;
    @Autowired UserRepository userRepository;
    @Autowired SecurityService securityService;

    /**
     * GET /vuelos : Obtener todos los vuelos.
     *
     * @return la lista de vuelos
     */
    @GetMapping("/vuelos")
    public List<Vuelo> getAllVuelos() {
        return vueloRepository.findAll();
    }

    /**
     * GET /vuelos/{id} : Obtener el vuelo por id.
     *
     * @param id el id del vuelo a recuperar
     * @return el ResponseEntity con estado 200 (OK) y con el cuerpo del vuelo, o con estado 404 (No Encontrado)
     */
    @GetMapping("/vuelos/{id}")
    public ResponseEntity<Vuelo> findById(@PathVariable String id) {
        if (vueloRepository.existsById(id)) {
            var vuelo = vueloRepository.findById(id).get();
            return new ResponseEntity<>(vuelo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * GET /vuelos/aerolinea/{aerolinea} : Obtener vuelos por aerolínea.
     *
     * @param aerolinea la aerolínea de los vuelos a recuperar
     * @return la lista de vuelos
     */
    @GetMapping("/vuelos/aerolinea/{aerolinea}")
    public List<Vuelo> findByAerolinea(@PathVariable String aerolinea) {
        return vueloRepository.findByAerolinea(aerolinea);
    }

    /**
     * GET /vuelos/destino/codigo/{codigo} : Obtener vuelos por código de destino.
     *
     * @param codigo el código de destino de los vuelos a recuperar
     * @return la lista de vuelos
     */
    @GetMapping("/vuelos/destino/codigo/{codigo}")
    public List<Vuelo> findByDestinoCodigo(@PathVariable String codigo) {
        return vueloRepository.findByDestinocod(codigo);
    }

    /**
     * GET /vuelos/destino/nombre/{nombre} : Obtener vuelos por nombre del destino.
     *
     * @param nombre el nombre del destino de los vuelos a recuperar
     * @return la lista de vuelos
     */
    @GetMapping("/vuelos/destino/nombre/{nombre}")
    public List<Vuelo> findByDestinoNombre(@PathVariable String nombre) {
        return vueloRepository.findByDestinonombre(nombre);
    }

    /**
     * GET /vuelos/origen/ciudad/{ciudad} : Obtener vuelos por ciudad de origen.
     *
     * @param ciudad la ciudad de origen de los vuelos a recuperar
     * @return la lista de vuelos
     */
    @GetMapping("/vuelos/origen/ciudad/{ciudad}")
    public List<Vuelo> findByOrigenCiudad(@PathVariable String ciudad) {
        return vueloRepository.findByOrigenciudad(ciudad);
    }

    /**
     * POST / : Crear un nuevo vuelo.
     *
     * @param vuelo el vuelo a crear
     * @return el ResponseEntity con estado 201 (Creado) y con el cuerpo del nuevo vuelo, o con estado 409 (Conflicto) si el vuelo ya existe
     */
    @PostMapping("/")
    public ResponseEntity<Vuelo> create(@RequestBody Vuelo vuelo) {
        if (vueloRepository.existsById(vuelo.getId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            vueloRepository.save(vuelo);
            return new ResponseEntity<>(vuelo, HttpStatus.CREATED);
        }
    }

    /**
     * PUT /vuelos/{id} : Actualizar un vuelo existente.
     *
     * @param id el id del vuelo a actualizar
     * @param vueloDetails los detalles del vuelo a actualizar
     * @return el ResponseEntity con estado 200 (OK) y con el cuerpo del vuelo actualizado, o con estado 404 (No Encontrado) si el vuelo no existe
     */
    @PutMapping("/vuelos/{id}")
    public ResponseEntity<Vuelo> update(@PathVariable String id, @RequestBody Vuelo vueloDetails) {
        return vueloRepository.findById(id).map(vuelo -> {
            vuelo.setNumeroVuelo(vueloDetails.getNumeroVuelo());
            vuelo.setAerolinea(vueloDetails.getAerolinea());
            vuelo.setOrigennombre(vueloDetails.getOrigennombre());
            vuelo.setOrigenciudad(vueloDetails.getOrigenciudad());
            vuelo.setOrigenpais(vueloDetails.getOrigenpais());
            vuelo.setDestinocod(vueloDetails.getDestinocod());
            vuelo.setDestinonombre(vueloDetails.getDestinonombre());
            vuelo.setDestinociudad(vueloDetails.getDestinociudad());
            vuelo.setDestinopais(vueloDetails.getDestinopais());
            vuelo.setHoraSalida(vueloDetails.getHoraSalida());
            vuelo.setHoraLlegada(vueloDetails.getHoraLlegada());
            vuelo.setEstado(vueloDetails.getEstado());
            vueloRepository.save(vuelo);
            return new ResponseEntity<>(vuelo, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE /vuelos/{id} : Eliminar el vuelo por id.
     *
     * @param id el id del vuelo a eliminar
     * @param token el token de seguridad para validación
     * @return el ResponseEntity con estado 204 (Sin Contenido) si la eliminación es exitosa, o con estado 401 (No Autorizado) si el token es inválido
     */
    @DeleteMapping("/vuelos/{id}")
    public ResponseEntity delete(@PathVariable String id, @RequestParam String token) {
        if (securityService.requestValidation(token)) {
            vueloRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}