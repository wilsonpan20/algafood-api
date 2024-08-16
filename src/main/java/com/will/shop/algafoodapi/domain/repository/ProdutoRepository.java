package com.will.shop.algafoodapi.domain.repository;

import com.will.shop.algafoodapi.domain.model.Produto;
import com.will.shop.algafoodapi.domain.model.Restaurante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	@Query("from Produto p where p.restaurante.id = :restauranteId and p.id = :produtoId")
	Optional<Produto> findById(@Param("restauranteId") Long restauranteId, @Param("produtoId") Long produtoId);

	List<Produto> findByRestaurante(Restaurante restaurante);
}
