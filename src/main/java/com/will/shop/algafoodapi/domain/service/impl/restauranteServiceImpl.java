package com.will.shop.algafoodapi.domain.service.impl;

import com.will.shop.algafoodapi.domain.exception.EntitadeEmUsoException;
import com.will.shop.algafoodapi.domain.exception.RestauranteNaoEncontradaException;
import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import com.will.shop.algafoodapi.domain.repository.CozinhaRepository;
import com.will.shop.algafoodapi.domain.repository.RestauranteRepository;
import com.will.shop.algafoodapi.domain.service.RestauranteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class restauranteServiceImpl implements RestauranteService {
	@Autowired
	RestauranteRepository restauranteRepository;

	@Autowired
	CozinhaRepository cozinhaRepository;

	@Override
	public List<Restaurante> listar() {
		List<Restaurante> restaurantes = restauranteRepository.findAll();
		return restaurantes;
	}

	@Override
	public Restaurante buscar(long restauranteId) {
		Restaurante restaurante = restauranteRepository.findById(restauranteId).orElseThrow(() -> {
			throw new RestauranteNaoEncontradaException(Restaurante.class, restauranteId);
		});
		return restaurante;
	}

	@Transactional
	@Override
	public Restaurante adcionar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> {
			throw new RestauranteNaoEncontradaException(Cozinha.class, cozinhaId);
		});

		restaurante.setCozinha(cozinha);

		return restauranteRepository.save(restaurante);
	}

	@Transactional
	@Override
	public Restaurante atualizar(long restauranteId, @Valid Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> {
			throw new RestauranteNaoEncontradaException(Cozinha.class, cozinhaId);
		});
		Restaurante restaurante1 = restauranteRepository.findById(restauranteId).orElseThrow(() -> {
			throw new RestauranteNaoEncontradaException(Restaurante.class, restauranteId);
		});

		BeanUtils.copyProperties(restaurante, restaurante1, "id", "formasPagamento", "endereco", "dataCadastro",
				"produtos");

		return restauranteRepository.save(restaurante1);
	}

	@Transactional
	@Override
	public void remover(Long restauranteId) {
		try {
			restauranteRepository.deleteById(restauranteId);
		} catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradaException(Restaurante.class, restauranteId);
		} catch (DataIntegrityViolationException e) {
			throw new EntitadeEmUsoException(Restaurante.class, restauranteId);
		}
	}
}
