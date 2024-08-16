package com.will.shop.algafoodapi.domain.repository;

import com.will.shop.algafoodapi.domain.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
}
