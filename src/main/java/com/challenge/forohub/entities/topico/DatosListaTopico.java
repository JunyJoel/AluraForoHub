package com.challenge.forohub.entities.topico;

import com.challenge.forohub.entities.curso.Curso;
import com.challenge.forohub.entities.usuario.Usuario;

import java.util.Date;

public record DatosListaTopico(Long id,
                               String titulo,
                               String mensage,
                               Date fecha,
                               Boolean status,
                               String autor,
                               String curso) {
    public DatosListaTopico(Topico topico, String autor, String curso) {
        this(topico.getId(), topico.getTitulo(), topico.getMensage(),topico.getFecha(), topico.getStatus(), autor, curso);
    }
}
