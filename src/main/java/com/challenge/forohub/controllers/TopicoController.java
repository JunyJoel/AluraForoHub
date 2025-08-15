package com.challenge.forohub.controllers;

import com.challenge.forohub.entities.ValidationException;
import com.challenge.forohub.entities.curso.Curso;
import com.challenge.forohub.entities.curso.CursoRepository;
import com.challenge.forohub.entities.topico.DatosRegistroTopico;
import com.challenge.forohub.entities.topico.DatosListaTopico;
import com.challenge.forohub.entities.topico.Topico;
import com.challenge.forohub.entities.topico.TopicoRepository;
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
        System.out.println("Topico Controller is working...");
        var topicoNuevo = new Topico(datos);
        if (topicoRepository.findByTitulo(topicoNuevo.getTitulo()).isPresent()) throw new ValidationException("El topico ya existe");
        topicoRepository.save(topicoNuevo);
        return ResponseEntity.ok(topicoNuevo);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listarTopicos(@PageableDefault(size =10, sort = "fecha") Pageable pageable) {
        var page = topicoRepository.findAllByStatusTrue(pageable)
                .map(topico -> {
                    // Fetch additional data
                    String autorNombre = usuarioRepository.findById(topico.getAutor_id())
                            .map(Usuario::getNombre)
                            .orElse("Desconocido");
                    // Build DTO with extra info (you may need to modify your DTO)
                    String cursoNombre = cursoRepository.findById(topico.getCurso_id())
                            .map(Curso::getNombre)
                            .orElse("Desconocido");
                    return new DatosListaTopico(topico, autorNombre, cursoNombre);
                });
        return ResponseEntity.ok(page);
    }

}
