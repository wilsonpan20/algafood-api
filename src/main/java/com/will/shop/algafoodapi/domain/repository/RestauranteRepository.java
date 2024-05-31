package com.will.shop.algafoodapi.domain.repository;

import com.will.shop.algafoodapi.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    List <Restaurante> listar();
    Restaurante getById(Long id);
    Restaurante adcionar(Restaurante restaurante);
    void remover(Restaurante restaurante);

}
