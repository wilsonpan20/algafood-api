package com.will.shop.algafoodapi.domain.service;

import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Estado;

import java.util.List;

public interface EstadoService {
    List<Estado> listar();
    Estado buscar(Long estadoId);
    Estado adcionar(Estado estado);
    Estado atualizar(long estadoId, Estado estado);
    void remover(Long estadoId);

}
