package com.will.shop.algafoodapi.domain.service;

import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Restaurante;

import java.util.List;

public interface RestauranteService {
	List<Restaurante> listar();

	Restaurante buscar(long restauranteId);

	Restaurante adcionar(Restaurante restaurante);

	Restaurante atualizar(long restauranteId, Restaurante restaurante);

	void remover(Long RestauranteId);
}
