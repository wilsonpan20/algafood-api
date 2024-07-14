package com.will.shop.algafoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }

    public EntidadeNaoEncontradaException(Class<?>entityClass,Long entityId){
        this(String.format("%s com Id %d não encontrada",entityClass.getSimpleName(),entityId));
    }
}