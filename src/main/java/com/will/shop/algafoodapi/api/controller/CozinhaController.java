package com.will.shop.algafoodapi.api.controller;


import com.will.shop.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.EntitadeEmUsoException;
import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.service.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/cozinhas")
public class CozinhaController {
    @Autowired
    private CozinhaService cozinhaService;


    @GetMapping
    public ResponseEntity<List<Cozinha>> listar() {
        List<Cozinha> cozinhas = cozinhaService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(cozinhas);
    }


    @GetMapping("/{cozinhaId}")
    public Cozinha buscar(@PathVariable Long cozinhaId) {
        return cozinhaService.buscar(cozinhaId);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Cozinha salvar(@RequestBody Cozinha cozinha) {
        Cozinha obCozinha = cozinhaService.adcionar(cozinha);
        return obCozinha;
    }

    @PutMapping("/{cozinhaId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Cozinha atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
           Cozinha cozinhaAtual =  cozinhaService.atualizar(cozinhaId, cozinha);
            return cozinhaAtual;

    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long cozinhaId) {
        cozinhaService.remover(cozinhaId);

    }
}
