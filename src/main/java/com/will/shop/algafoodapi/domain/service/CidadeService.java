package com.will.shop.algafoodapi.domain.service;

import com.will.shop.algafoodapi.domain.model.Cidade;

import java.util.List;

public interface CidadeService {
	List<Cidade> listar();

	Cidade buscar(Long cidadeId);

	Cidade adcionar(Cidade cidade);

	Cidade atualizar(Long ciaddeId, Cidade cidade);

	void remover(Long cidadeId);

}
