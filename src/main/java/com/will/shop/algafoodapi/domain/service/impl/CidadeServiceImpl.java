package com.will.shop.algafoodapi.domain.service.impl;

import com.will.shop.algafoodapi.domain.exception.CidadeNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.EntitadeEmUsoException;
import com.will.shop.algafoodapi.domain.exception.EstadoNaoEncontradaException;
import com.will.shop.algafoodapi.domain.model.Cidade;
import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Estado;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import com.will.shop.algafoodapi.domain.repository.CidadeRepository;
import com.will.shop.algafoodapi.domain.repository.EstadoRepository;
import com.will.shop.algafoodapi.domain.service.CidadeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeServiceImpl implements CidadeService {
	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	EstadoRepository estadoRepository;

	@Override
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	@Override
	public Cidade buscar(Long cidadeId) {
		Cidade cidade = cidadeRepository.findById(cidadeId).orElseThrow(() -> {
			throw new CidadeNaoEncontradaException(Cidade.class, cidadeId);
		});
		return cidade;
	}

	@Transactional
	@Override
	public Cidade adcionar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.findById(estadoId).orElseThrow(() -> {
			throw new EstadoNaoEncontradaException(Estado.class, estadoId);

		});

		cidade.setEstado(estado);

		return cidadeRepository.save(cidade);
	}

	@Transactional
	@Override
	public Cidade atualizar(Long cidadeId, Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.findById(estadoId).orElseThrow(() -> {
			throw new EstadoNaoEncontradaException(Estado.class, estadoId);
		});

		Cidade cidade1 = cidadeRepository.findById(cidadeId).orElseThrow(() -> {
			throw new CidadeNaoEncontradaException(Cidade.class, cidadeId);
		});
		BeanUtils.copyProperties(cidade, cidade1, "id");

		return cidadeRepository.save(cidade1);
	}

	@Transactional
	@Override
	public void remover(Long cidadeId) {

		Cidade cidade = cidadeRepository.findById(cidadeId)
				.orElseThrow(() -> new CidadeNaoEncontradaException(Cidade.class, cidadeId));
		try {
			cidadeRepository.deleteById(cidadeId);
			cidadeRepository.flush();

		} catch (DataIntegrityViolationException e) {
			throw new EntitadeEmUsoException(Cidade.class, cidadeId);
		}
	}
}
