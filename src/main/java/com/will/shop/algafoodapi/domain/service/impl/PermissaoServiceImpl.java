package com.will.shop.algafoodapi.domain.service.impl;

import com.will.shop.algafoodapi.domain.exception.PermissaoNaoEncontradaException;
import com.will.shop.algafoodapi.domain.model.Permissao;
import com.will.shop.algafoodapi.domain.repository.PermissaoRepository;
import com.will.shop.algafoodapi.domain.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissaoServiceImpl implements PermissaoService {
	@Autowired
	private PermissaoRepository permissaoRepository;

	@Override
	public Permissao buscar(Long pemissaoId) {
		return permissaoRepository.findById(pemissaoId)
				.orElseThrow(() -> new PermissaoNaoEncontradaException(Permissao.class, pemissaoId));
	}
}
