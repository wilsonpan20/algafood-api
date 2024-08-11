package com.will.shop.algafoodapi.domain.service;

import com.will.shop.algafoodapi.domain.model.Usuario;

import java.util.List;

public interface UsuarioService {
	List<Usuario> listar();
	Usuario buscar(Long usuarioId);
	Usuario adcionar(Usuario usuario);
	void alterarSenha(Long usuarioId,String senhaAtual,String novaSenha);
	void remove(Long usuarioId);
}
