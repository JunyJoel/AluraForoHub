package com.challenge.forohub.controllers;

import com.challenge.forohub.entities.ValidationException;
import com.challenge.forohub.entities.curso.Curso;
import com.challenge.forohub.entities.curso.CursoRepository;
import com.challenge.forohub.entities.topico.*;
import com.challenge.forohub.entities.usuario.Usuario;
import com.challenge.forohub.entities.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {
        var topicoNuevo = new Topico(datos);
        if (topicoRepository.findByTitulo(topicoNuevo.getTitulo()).isPresent()) throw new ValidationException("El topico ya existe");
        topicoRepository.save(topicoNuevo);
        return ResponseEntity.ok(topicoNuevo);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listarTopicos(@PageableDefault(size =10, sort = "fecha") Pageable pageable) {
        var page = topicoRepository.findAllByStatusTrue(pageable)
                .map(topico -> {
                    String autorNombre = usuarioRepository.findById(topico.getAutor_id())
                            .map(Usuario::getNombre)
                            .orElse("Desconocido");
                    String cursoNombre = cursoRepository.findById(topico.getCurso_id())
                            .map(Curso::getNombre)
                            .orElse("Desconocido");
                    return new DatosListaTopico(topico, autorNombre, cursoNombre);
                });
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListaTopico> listarTopicoPorId(@PathVariable Long id) {
        if (!topicoRepository.existsById(id)) throw new ValidationException("El topico no existe");

        var topicoSolo = topicoRepository.findById(id)
                .map(topico -> {
                    String autorNombre = usuarioRepository.findById(topico.getAutor_id())
                            .map(Usuario::getNombre)
                            .orElse("Desconocido");
                    String cursoNombre = cursoRepository.findById(topico.getCurso_id())
                            .map(Curso::getNombre)
                            .orElse("Desconocido");
                    return new DatosListaTopico(topico, autorNombre, cursoNombre);
                })
                .orElse(null);
        return ResponseEntity.ok(topicoSolo);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datos) {
        //verificar que existe el registro segun el id
        if (!topicoRepository.existsById(id)) throw new ValidationException("El topico no existe");
        //verificar que el usuario exista en la DB
        if (!usuarioRepository.findByNombre(datos.usuarioLoggeado()).isPresent()) throw new ValidationException("El usuario no existe en DB");
        //verificar que el autor del topico sea el mismo que el usuario loggeado
        var usuarioLoggeadoId = usuarioRepository.findByNombre(datos.usuarioLoggeado()).get().getId();//obtener Id del usuario loggeado a partir del nombre
        if (!topicoRepository.findById(id).get().getAutor_id().equals(usuarioLoggeadoId)) throw new ValidationException("Acceso denegado...\n\n...El topico solo lo puede modificar el usuario que lo creo.");

        //obtener datos correctos para generar el topico y actualizar
        var autorId = usuarioRepository.findByNombre(datos.usuarioLoggeado()).get().getId(); //obtener Id del usuario loggeado
        var cursoId = cursoRepository.findByNombre(datos.curso()).get().getId(); //obtener Id del curso seleccionado
        var topico = topicoRepository.findById(id).get();
        topico.actualizarTopico(datos, autorId, cursoId);
        return ResponseEntity.ok(topico);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        //verificar que existe el registro segun el id
        if (!topicoRepository.existsById(id)) throw new ValidationException("El topico no existe");
        var topico = topicoRepository.findById(id).get();
        topico.eliminarTopico();
        return ResponseEntity.ok(topico);
    }

}
