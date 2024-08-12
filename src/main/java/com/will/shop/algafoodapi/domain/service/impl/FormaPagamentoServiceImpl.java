package com.will.shop.algafoodapi.domain.service.impl;

import com.will.shop.algafoodapi.domain.exception.EntitadeEmUsoException;
import com.will.shop.algafoodapi.domain.exception.FormaPagamentoNaoEncontradaException;
import com.will.shop.algafoodapi.domain.model.FormaPagamento;
import com.will.shop.algafoodapi.domain.repository.FormaPagamentoRepository;
import com.will.shop.algafoodapi.domain.service.FormaPagamentoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FormaPagamentoServiceImpl implements FormaPagamentoService {

	@Autowired
	private final FormaPagamentoRepository repository;

	@Override
	public List<FormaPagamento> listar() {
		return repository.findAll();
	}

	@Override
	public FormaPagamento buscar(Long formaPagamentoId) {
		return repository.findById(formaPagamentoId)
				.orElseThrow(() -> new FormaPagamentoNaoEncontradaException(FormaPagamento.class, formaPagamentoId));
	}

	@Transactional
	@Override
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return repository.save(formaPagamento);
	}

	@Transactional
	@Override
	public void delete(Long formaPagamentoId) {
		FormaPagamento formaPagamento = buscar(formaPagamentoId);
		repository.flush();
		try {
			repository.deleteById(formaPagamentoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntitadeEmUsoException(FormaPagamento.class, formaPagamentoId);
		}
	}
}
