package com.will.shop.algafoodapi.domain.service;

import com.will.shop.algafoodapi.api.model.dto.request.RestauranteRequestDto;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import jakarta.validation.Valid;

import java.util.List;

public interface RestauranteService {
	List<Restaurante> listar();

	Restaurante buscar(long restauranteId);

	Restaurante adcionar(Restaurante restaurante);

	void remover(Long RestauranteId);

	void ativar(Long restauranteId);

	void inativar(Long restauranteId);

	void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId);

	void adcionarFormaPagamento(Long restauranteId, Long formaPagamentoId);

}
