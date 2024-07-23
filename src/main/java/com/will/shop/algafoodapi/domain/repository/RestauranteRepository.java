package com.will.shop.algafoodapi.domain.repository;

import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries,
		JpaSpecificationExecutor<Restaurante> {
	List<Restaurante> findByTaxaFreteBetween(BigDecimal inicialTaxa, BigDecimal finalTaxa);

	//List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
	@Query("from Restaurante r join r.cozinha left join r.formasPagamento left join r.endereco")
	List<Restaurante> findAll();

	//@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);

	Optional<Restaurante> findFistByNomeContaining(String nome);

	int countByCozinhaId(Long cozinha);

}
