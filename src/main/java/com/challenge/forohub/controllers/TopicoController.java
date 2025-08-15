package com.challenge.forohub.controllers;

import com.challenge.forohub.entities.topico.DatosRegistroTopico;
import com.challenge.forohub.entities.topico.Topico;
import com.challenge.forohub.entities.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public void registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {
        var topicoNuevo = new Topico(datos);
        topicoRepository.save(topicoNuevo);
    }
}
