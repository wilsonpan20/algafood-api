package com.will.shop.algafoodapi.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;


    public CozinhaNaoEncontradaException(String message) {
        super(message);
    }

    public CozinhaNaoEncontradaException(Class<?>entityClass, Long entityId){
        this(String.format("%s com Id %d não encontrada",entityClass.getSimpleName(),entityId));
    }
}
