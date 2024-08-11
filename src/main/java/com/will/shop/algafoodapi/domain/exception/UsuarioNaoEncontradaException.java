package com.will.shop.algafoodapi.domain.exception;

public class UsuarioNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;


    public UsuarioNaoEncontradaException(String message) {
        super(message);
    }

    public UsuarioNaoEncontradaException(Class<?>entityClass, Long entityId){
        this(String.format("%s com Id %d não encontrada",entityClass.getSimpleName(),entityId));
    }
}
