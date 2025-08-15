package com.challenge.forohub.entities.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "topicos")
@Entity(name = "Topico")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensage;
    private Date fecha_creacion;
    private int status;
    private Long autor_id;
    private Long curso_id;

    public Topico(DatosRegistroTopico datos) {
        this.titulo = datos.titulo();
        this.mensage = datos.mensage();
        this.fecha_creacion = new Date();
        this.status = 1;
        this.autor_id = datos.autor_id();
        this.curso_id = datos.curso_id();
    }
}
