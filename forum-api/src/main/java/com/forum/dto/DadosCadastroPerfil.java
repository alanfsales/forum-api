package com.forum.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroPerfil(
        @NotBlank
        String nome) {
}
