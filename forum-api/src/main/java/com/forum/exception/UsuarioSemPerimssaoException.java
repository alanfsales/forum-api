package com.forum.exception;

public class UsuarioSemPerimssaoException extends RuntimeException{

    public UsuarioSemPerimssaoException(String mensagem){
        super(mensagem);
    }
}
