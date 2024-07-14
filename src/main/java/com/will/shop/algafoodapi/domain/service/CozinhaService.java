package com.will.shop.algafoodapi.domain.service;

import com.will.shop.algafoodapi.domain.model.Cozinha;

import java.util.List;
import java.util.Optional;


public interface CozinhaService {
    List<Cozinha> listar();

    Cozinha buscar(long cozinhaId);

    Cozinha adcionar(Cozinha cozinha);

    Cozinha atualizar(long cozinhaId, Cozinha cozinha);

    void remover(Long id);
}
