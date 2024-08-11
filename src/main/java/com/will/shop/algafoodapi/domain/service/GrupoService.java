package com.will.shop.algafoodapi.domain.service;

import com.will.shop.algafoodapi.domain.model.Grupo;

import java.util.List;

public interface GrupoService {
	List<Grupo> listar();

	Grupo buscar(Long grupoId);

	Grupo adcionar(Grupo grupo);

	void remove(Long grupoId);
}
