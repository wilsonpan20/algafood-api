package com.will.shop.algafoodapi.domain.exception;

public class RestauranteNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;


    public RestauranteNaoEncontradaException(String message) {
        super(message);
    }

    public RestauranteNaoEncontradaException(Class<?>entityClass, Long entityId){
        this(String.format("%s com Id %d não encontrada",entityClass.getSimpleName(),entityId));
    }
}
