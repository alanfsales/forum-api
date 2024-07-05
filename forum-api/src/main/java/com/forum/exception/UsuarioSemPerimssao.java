package com.forum.exception;

public class UsuarioSemPerimssao extends RuntimeException{

    public UsuarioSemPerimssao(String mensagem){
        super(mensagem);
    }
}
