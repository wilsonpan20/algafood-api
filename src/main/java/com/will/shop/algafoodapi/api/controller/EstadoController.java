package com.will.shop.algafoodapi.api.controller;


import com.will.shop.algafoodapi.domain.model.Estado;
import com.will.shop.algafoodapi.domain.service.EstadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/estados")
public class EstadoController {
    @Autowired
    private EstadoService estadoService;

    @GetMapping
    List<Estado> listar() {
        List<Estado> estados = estadoService.listar();
        return estados;
    }

    @GetMapping("/{estadoId}")
      Estado buscar(@PathVariable Long estadoId) {
        Estado estado = estadoService.buscar(estadoId);
        return estado;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    Estado adcionar(@RequestBody @Valid Estado estado) {
        estado = estadoService.adcionar(estado);
        return estado;
    }

    @PutMapping("/{estadoId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    Estado altualizar(@PathVariable Long estadoId, @RequestBody @Valid Estado estado) {
        Estado estadoAtual = estadoService.atualizar(estadoId, estado);
        return estadoAtual;
    }

    @DeleteMapping("/{estadoId}")
    public void excluir(@PathVariable Long estadoId) {
            estadoService.remover(estadoId);
    }
}
