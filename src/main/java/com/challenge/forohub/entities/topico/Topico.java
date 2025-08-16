package com.challenge.forohub.entities.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
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
    private Date fecha;
    private Boolean status;
    private Long autor_id;
    private Long curso_id;

    public Topico(DatosRegistroTopico datos) {
        this.titulo = datos.titulo();
        this.mensage = datos.mensage();
        this.fecha = new Date();
        this.status = true;
        this.autor_id = datos.autor_id();
        this.curso_id = datos.curso_id();
    }

    public void actualizarTopico(@Valid DatosActualizacionTopico datos, Long autor, Long curso) {
        if (datos.titulo() != null) this.titulo = datos.titulo();
        if (datos.mensage() != null) this.mensage = datos.mensage();
        if (curso != null) this.curso_id = curso;
    }

    public void eliminarTopico() {
        this.status = false;
    }
}
