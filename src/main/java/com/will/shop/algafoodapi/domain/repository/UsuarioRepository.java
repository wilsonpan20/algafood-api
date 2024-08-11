package com.will.shop.algafoodapi.domain.repository;

import com.will.shop.algafoodapi.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
}
