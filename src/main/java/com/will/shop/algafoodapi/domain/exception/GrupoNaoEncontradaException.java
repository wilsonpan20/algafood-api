package com.will.shop.algafoodapi.domain.exception;

public class GrupoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;


    public GrupoNaoEncontradaException(String message) {
        super(message);
    }

    public GrupoNaoEncontradaException(Class<?>entityClass, Long entityId){
        this(String.format("%s com Id %d não encontrada",entityClass.getSimpleName(),entityId));
    }
}
