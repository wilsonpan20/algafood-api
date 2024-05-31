package com.will.shop.algafoodapi.domain.repository;

import com.will.shop.algafoodapi.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {
    List<Cozinha> listar();
    Cozinha buscar(Long id);
    Cozinha adcionar(Cozinha cozinha);
    void remover(Cozinha cozinha);
}
