package com.will.shop.algafoodapi.domain.service.impl;

import com.will.shop.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.EntitadeEmUsoException;
import com.will.shop.algafoodapi.domain.exception.EstadoNaoEncontradaException;
import com.will.shop.algafoodapi.domain.model.Estado;
import com.will.shop.algafoodapi.domain.repository.EstadoRepository;
import com.will.shop.algafoodapi.domain.service.EstadoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoServiceImpl implements EstadoService {
	@Autowired
	private EstadoRepository estadoRepository;

	@Override
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}

	@Override
	public Estado atualizar(long estadoId, Estado estado) {
		Estado estadoAtual = estadoRepository.findById(estadoId).orElseThrow(() -> {
			throw new EstadoNaoEncontradaException(Estado.class, estadoId);
		});
		BeanUtils.copyProperties(estado, estadoAtual, "id");

		estadoRepository.save(estadoAtual);

		return estadoAtual;

	}

	@Override
	public Estado buscar(Long estadoId) {
		return estadoRepository.findById(estadoId).orElseThrow(() -> {
			throw new EstadoNaoEncontradaException(Estado.class, estadoId);

		});
	}

	@Transactional
	@Override
	public Estado adcionar(Estado estado) {
		return estadoRepository.save(estado);
	}

	@Transactional
	@Override
	public void remover(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradaException(Estado.class, estadoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntitadeEmUsoException(Estado.class, estadoId);
		}
	}
}
