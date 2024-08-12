package com.will.shop.algafoodapi.domain.service.impl;

import com.will.shop.algafoodapi.domain.exception.NegocioException;
import com.will.shop.algafoodapi.domain.exception.UsuarioNaoEncontradaException;
import com.will.shop.algafoodapi.domain.model.Usuario;
import com.will.shop.algafoodapi.domain.repository.UsuarioRepository;
import com.will.shop.algafoodapi.domain.service.UsuarioService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario buscar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new UsuarioNaoEncontradaException(Usuario.class, usuarioId));
	}

	@Override
	@Transactional
	public Usuario adcionar(Usuario usuario) {
		usuarioRepository.detach(usuario);

		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException(
					String.format("Jã existe um usuario cadastrado com o e-mail %s", usuario.getEmail()));
		}

		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = buscar(usuarioId);
		if (usuario.senhaNaoCoincideCom(senhaAtual)) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuario");
		}
		usuario.setSenha(novaSenha);
	}

	@Override
	@Transactional
	public void remove(Long usuarioId) {
		Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> {
			throw new UsuarioNaoEncontradaException(Usuario.class, usuarioId);
		});
		try {
			usuarioRepository.deleteById(usuarioId);
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException(e.getMessage());
		}
	}
}
