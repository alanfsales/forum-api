package com.forum.dto.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(
        @NotBlank
        String nome,

        @Email
        @NotBlank
        String email,

        @NotBlank
        String senha,

        @NotNull
        Long perfilId) {

}
