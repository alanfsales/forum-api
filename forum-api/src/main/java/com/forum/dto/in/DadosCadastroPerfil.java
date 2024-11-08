package com.forum.dto.in;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroPerfil(
        @NotBlank
        String nome) {
}
