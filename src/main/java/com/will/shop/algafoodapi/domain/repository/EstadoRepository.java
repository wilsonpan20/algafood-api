package com.will.shop.algafoodapi.domain.repository;

import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> listar();
    Estado buscar(Long id);
    Estado adcionar(Estado estado);
    void remover(Estado estado);
}
