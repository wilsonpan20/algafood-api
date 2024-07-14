package com.will.shop.algafoodapi.api.controller;

import com.will.shop.algafoodapi.api.exceptionhandler.Problema;
import com.will.shop.algafoodapi.domain.exception.CidadeNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.EntitadeEmUsoException;
import com.will.shop.algafoodapi.domain.exception.NegocioException;
import com.will.shop.algafoodapi.domain.model.Cidade;
import com.will.shop.algafoodapi.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("v1/cidades")
public class CidadeController {
    @Autowired
    CidadeService cidadeService;

    @GetMapping
    List<Cidade> listar() {
        List<Cidade> cidades = cidadeService.listar();
        return cidades;
    }

    @GetMapping("/{cidadeId}")
    Cidade buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cidadeService.buscar(cidadeId);
        return cidade;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Cidade adcionar(@RequestBody Cidade cidade) {
        try {
            cidade = cidadeService.adcionar(cidade);
            return cidade;

        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    Cidade atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
        try {
            cidade = cidadeService.atualizar(cidadeId, cidade);
            return cidade;
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long cidadeId) {
        cidadeService.remover(cidadeId);
    }
}
