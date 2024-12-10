package com.forum.exception;

public class TokenInvalidoException extends RuntimeException {
    public TokenInvalidoException(String messagem) {
        super(messagem);
    }
}
