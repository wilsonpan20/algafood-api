package com.will.shop.algafoodapi.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static Long serialVersion = 1L;

	public ProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
		this(String.format("Não existe um cadastro de produto com codigo %d para o restaurante de codigo %d", produtoId,
				restauranteId));
	}

}
