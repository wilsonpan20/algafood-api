package com.will.shop.algafoodapi.domain.service.impl;

import com.will.shop.algafoodapi.domain.exception.CozinhaNaoEncontradaException;

import com.will.shop.algafoodapi.domain.exception.EntitadeEmUsoException;
import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.repository.CozinhaRepository;
import com.will.shop.algafoodapi.domain.service.CozinhaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaServiceImpl implements CozinhaService {

	private CozinhaRepository cozinhaRepository;

	@Autowired
	public CozinhaServiceImpl(CozinhaRepository cozinhaRepository) {
		this.cozinhaRepository = cozinhaRepository;
	}

	@Override
	public List<Cozinha> listar() {
		List<Cozinha> cozinhas = cozinhaRepository.findAll();

		return cozinhas;
	}

	@Override
	public Cozinha buscar(long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new CozinhaNaoEncontradaException(Cozinha.class, cozinhaId));
	}

	@Transactional
	@Override
	public Cozinha adcionar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	public Cozinha atualizar(long cozinhaId, Cozinha cozinha) {

		Cozinha cozinha1 = cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new CozinhaNaoEncontradaException(Cozinha.class, cozinhaId));

		cozinhaRepository.save(cozinha1);

		return cozinha1;
	}

	@Transactional
	@Override
	public void remover(Long cozinhaId) {

		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new CozinhaNaoEncontradaException(Cozinha.class, cozinhaId));
		try {
			cozinhaRepository.deleteById(cozinhaId);
			cozinhaRepository.flush();

		} catch (DataIntegrityViolationException e) {
			throw new EntitadeEmUsoException(Cozinha.class, cozinhaId);
		}
	}
}
