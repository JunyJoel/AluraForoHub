package com.challenge.forohub.entities.respuesta;

//import jakarta.persistence.*;

import java.util.Date;

//@Entity
public class Respuesta {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensage;
    private Date fecha_creacion;
    private Long topico_id;
    private Long autor_id;

    public Respuesta(DatosRegistroRespuesta datos) {
        this.mensage = datos.mensaje();

        this.fecha_creacion = datos.fecha_creacion();
        this.topico_id = datos.topico_id();
        this.autor_id = datos.autor_id();
    }
}
