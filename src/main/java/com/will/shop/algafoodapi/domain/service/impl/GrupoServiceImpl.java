package com.will.shop.algafoodapi.domain.service.impl;

import com.will.shop.algafoodapi.domain.exception.EntitadeEmUsoException;
import com.will.shop.algafoodapi.domain.exception.GrupoNaoEncontradaException;
import com.will.shop.algafoodapi.domain.model.Grupo;
import com.will.shop.algafoodapi.domain.repository.GrupoRepository;
import com.will.shop.algafoodapi.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoServiceImpl implements GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;

	@Override
	public List<Grupo> listar() {
		return grupoRepository.findAll();
	}

	@Override
	public Grupo buscar(Long grupoId) {
		Grupo grupo = grupoRepository.findById(grupoId).orElseThrow(() -> {
			throw new GrupoNaoEncontradaException(Grupo.class, grupoId);
		});
		return grupo;
	}

	@Override
	public Grupo adcionar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}

	@Override
	public void remove(Long grupoId) {
		Grupo grupoExistente = grupoRepository.findById(grupoId).orElseThrow(() -> {
			throw new GrupoNaoEncontradaException(Grupo.class, grupoId);
		});
		try {
			grupoRepository.deleteById(grupoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntitadeEmUsoException(Grupo.class, grupoId);
		}
	}
}
