package com.will.shop.algafoodapi.api.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.will.shop.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.EntitadeEmUsoException;
import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Estado;
import com.will.shop.algafoodapi.domain.repository.EstadoRepository;
import com.will.shop.algafoodapi.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    Estado adcionar(@RequestBody Estado estado) {
        estado = estadoService.adcionar(estado);
        return estado;
    }

    @PutMapping("/{estadoId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    Estado altualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
        Estado estadoAtual = estadoService.atualizar(estadoId, estado);
        return estadoAtual;
    }

    @DeleteMapping("/{estadoId}")
    public void excluir(@PathVariable Long estadoId) {
            estadoService.remover(estadoId);
    }
}
