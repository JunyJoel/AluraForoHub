package com.challenge.forohub.entities.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(@NotBlank(message = "El título es obligatorio") String titulo,
                                  @NotBlank(message = "El mensaje es obligatorio") String mensage,
                                  @NotNull(message = "El id del autor es obligatorio") Long autor_id,
                                  @NotNull(message = "El id del curso es obligatorio") Long curso_id) {
}
