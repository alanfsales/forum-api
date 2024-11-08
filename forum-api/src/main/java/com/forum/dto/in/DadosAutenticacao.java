package com.forum.dto.in;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(
        @NotBlank
        String email,

        @NotBlank
        String senha) {
}
