package com.will.shop.algafoodapi.domain.repository;

import com.will.shop.algafoodapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository  extends CustomJpaRepository<Cozinha,Long> {
    List<Cozinha>findByNomeContaining(String nome);
    Optional<Cozinha> findFirstBynomeContaining(String nome);
    List<Cozinha> findTop2BynomeContaining(String nome);
    Boolean existsByNome(String nome);
}

