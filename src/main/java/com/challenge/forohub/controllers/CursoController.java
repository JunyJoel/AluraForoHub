package com.challenge.forohub.controllers;

import com.challenge.forohub.entities.ValidationException;
import com.challenge.forohub.entities.curso.Curso;
import com.challenge.forohub.entities.curso.CursoRepository;
import com.challenge.forohub.entities.curso.DatosRegistroCurso;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarCurso(@RequestBody @Valid DatosRegistroCurso curso) {
        var cursoNuevo = new Curso(curso);
        if (cursoRepository.findByNombre(cursoNuevo.getNombre()).isPresent()) throw new ValidationException("El curso ya existe");
        cursoRepository.save(cursoNuevo);
        return ResponseEntity.ok(cursoNuevo);
    }
}
