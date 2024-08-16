package com.will.shop.algafoodapi.domain.service;

import com.will.shop.algafoodapi.domain.model.Produto;

public interface ProdutoService {
	Produto salvar(Produto produto);
	Produto buscar(Long restauranteId,Long produtoId);
}
