package com.will.shop.algafoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EstadoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;


    public EstadoNaoEncontradaException(String message) {
        super(message);
    }

    public EstadoNaoEncontradaException(Class<?>entityClass, Long entityId){
        this(String.format("%s com Id %d não encontrada",entityClass.getSimpleName(),entityId));
    }
}
