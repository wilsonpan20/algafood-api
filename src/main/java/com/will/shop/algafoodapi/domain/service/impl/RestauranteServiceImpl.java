package com.will.shop.algafoodapi.domain.service.impl;

import com.will.shop.algafoodapi.api.assembler.restauranteasssembler.RestauranteRequestDtoAssembler;
import com.will.shop.algafoodapi.domain.exception.CidadeNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.EntitadeEmUsoException;
import com.will.shop.algafoodapi.domain.exception.RestauranteNaoEncontradaException;
import com.will.shop.algafoodapi.domain.model.Cidade;
import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import com.will.shop.algafoodapi.domain.repository.CidadeRepository;
import com.will.shop.algafoodapi.domain.repository.CozinhaRepository;
import com.will.shop.algafoodapi.domain.repository.RestauranteRepository;
import com.will.shop.algafoodapi.domain.service.RestauranteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteServiceImpl implements RestauranteService {

	@Autowired
	RestauranteRepository restauranteRepository;

	@Autowired
	CozinhaRepository cozinhaRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}

	@Override
	public Restaurante buscar(long restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(() -> {
			throw new RestauranteNaoEncontradaException(Restaurante.class, restauranteId);
		});
	}

	@Transactional
	@Override
	public Restaurante adcionar(Restaurante restaurante) {

		Long cozinhaId = restaurante.getCozinha().getId();
		Long cidadeId = restaurante.getEndereco().getCidade().getId();

		Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> {
			throw new RestauranteNaoEncontradaException(Cozinha.class, cozinhaId);
		});
		Cidade cidade = cidadeRepository.findById(cidadeId).orElseThrow(() -> {
			throw new CidadeNaoEncontradaException(Cidade.class, cidadeId);
		});

		restaurante.getEndereco().setCidade(cidade);

		restaurante.setCozinha(cozinha);

		return restauranteRepository.save(restaurante);
	}

	@Transactional
	public void ativar(Long restaurantId) {

		Restaurante restauranteExistente = buscar(restaurantId);
		restauranteExistente.ativar();
	}

	@Transactional
	public void inativar(Long restaurantId) {

		Restaurante restauranteExistente = buscar(restaurantId);
		restauranteExistente.inativar();
	}

	@Transactional
	@Override
	public void remover(Long restauranteId) {

		restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradaException(Restaurante.class, restauranteId));
		try {
			restauranteRepository.deleteById(restauranteId);
			restauranteRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntitadeEmUsoException(Restaurante.class, restauranteId);
		}
	}
}
