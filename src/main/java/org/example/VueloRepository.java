package org.example;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repositorio para la entidad Vuelo, proporciona métodos para realizar operaciones CRUD en MongoDB.
 */
public interface VueloRepository extends MongoRepository<Vuelo, String> {

    /**
     * Busca vuelos por el código del destino.
     *
     * @param destinocod el código del aeropuerto de destino
     * @return una lista de vuelos que tienen el código de destino especificado
     */
    public List<Vuelo> findByDestinocod(String destinocod);

    /**
     * Busca vuelos por el nombre del destino.
     *
     * @param destinonombre el nombre del aeropuerto de destino
     * @return una lista de vuelos que tienen el nombre de destino especificado
     */
    public List<Vuelo> findByDestinonombre(String destinonombre);

    /**
     * Busca vuelos por la aerolínea.
     *
     * @param aerolinea el nombre de la aerolínea
     * @return una lista de vuelos operados por la aerolínea especificada
     */
    public List<Vuelo> findByAerolinea(String aerolinea);

    /**
     * Busca vuelos por la ciudad de origen.
     *
     * @param ciudad el nombre de la ciudad de origen
     * @return una lista de vuelos que tienen la ciudad de origen especificada
     */
    public List<Vuelo> findByOrigenciudad(String ciudad);
}