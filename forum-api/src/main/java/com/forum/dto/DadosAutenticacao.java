package com.forum.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(
        @NotBlank
        String email,

        @NotBlank
        String senha) {
}
