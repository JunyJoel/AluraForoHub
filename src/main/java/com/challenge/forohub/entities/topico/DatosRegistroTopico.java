package com.challenge.forohub.entities.topico;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record DatosRegistroTopico(@NotBlank(message = "El titulo no puede estar vacio") String titulo,
                                  @NotBlank(message = "El mensaje no puede estar vacio") String mensage,
                                  @NotBlank(message = "El ID del autor no puede estar vacio") Long autor_id,
                                  @NotBlank(message = "El ID del curso no puede estar vacio") Long curso_id) {
}
