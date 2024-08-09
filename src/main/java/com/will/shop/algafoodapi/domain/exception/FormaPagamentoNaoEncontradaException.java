package com.will.shop.algafoodapi.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;


    public FormaPagamentoNaoEncontradaException(String message) {
        super(message);
    }

    public FormaPagamentoNaoEncontradaException(Class<?>entityClass, Long entityId){
        this(String.format("%s com Id %d não encontrada",entityClass.getSimpleName(),entityId));
    }
}
