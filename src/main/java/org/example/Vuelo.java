package org.example;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Representa una entidad de Vuelo almacenada en la colección "Vuelos" en MongoDB.
 */
@Document(collection = "Vuelos")
@Data
public class Vuelo {
    /**
     * El identificador único del vuelo.
     */
    @Id
    @Field("_id")
    private String id;

    /**
     * El número del vuelo.
     */
    @Field("numero_vuelo")
    private String numeroVuelo;

    /**
     * La aerolínea que opera el vuelo.
     */
    private String aerolinea;

    /**
     * El código del aeropuerto de origen.
     */
    @Field("origen_cod")
    private String origencod;

    /**
     * El nombre del aeropuerto de origen.
     */
    @Field("origen_nombre")
    private String origennombre;

    /**
     * La ciudad del aeropuerto de origen.
     */
    @Field("origen_ciudad")
    private String origenciudad;

    /**
     * El país del aeropuerto de origen.
     */
    @Field("origen_pais")
    private String origenpais;

    /**
     * El código del aeropuerto de destino.
     */
    @Field("destino_cod")
    private String destinocod;

    /**
     * El nombre del aeropuerto de destino.
     */
    @Field("destino_nombre")
    private String destinonombre;

    /**
     * La ciudad del aeropuerto de destino.
     */
    @Field("destino_ciudad")
    private String destinociudad;

    /**
     * El país del aeropuerto de destino.
     */
    @Field("destino_pais")
    private String destinopais;

    /**
     * La hora de salida del vuelo.
     */
    @Field("hora_salida")
    private String horaSalida;

    /**
     * La hora de llegada del vuelo.
     */
    @Field("hora_llegada")
    private String horaLlegada;

    /**
     * El estado del vuelo.
     */
    private String estado;
}
