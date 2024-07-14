package com.will.shop.algafoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EntitadeEmUsoException extends  NegocioException {
    private static final long serialVersionUID = 1L;

    public EntitadeEmUsoException(String message) {
        super(message);
    }
    public EntitadeEmUsoException(Class<?> entityClass,Long entityId){
        this(String.format("%s com Id %d nao pode ser removida,pois esta em uso",entityClass.getSimpleName(),entityId));
    }
}
