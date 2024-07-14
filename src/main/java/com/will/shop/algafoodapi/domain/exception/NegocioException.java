package com.will.shop.algafoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NegocioException extends RuntimeException{
    public NegocioException(String message) {
        super(message);
    }

    public NegocioException(String message,Throwable causa){
        super(message,causa);
    }

}

