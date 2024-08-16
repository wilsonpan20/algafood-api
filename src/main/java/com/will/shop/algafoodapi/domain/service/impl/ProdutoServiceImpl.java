package com.will.shop.algafoodapi.domain.service.impl;

import com.will.shop.algafoodapi.domain.exception.ProdutoNaoEncontradoException;
import com.will.shop.algafoodapi.domain.model.Produto;
import com.will.shop.algafoodapi.domain.repository.ProdutoRepository;
import com.will.shop.algafoodapi.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Override
	public Produto salvar(Produto produto) {
		return repository.save(produto);
	}

	@Override
	public Produto buscar(Long restauranteId, Long produtoId) {
		return repository.findById(restauranteId, produtoId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
	}
}
