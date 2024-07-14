package com.will.shop.algafoodapi.api.controller;


import com.will.shop.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.EntitadeEmUsoException;
import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.repository.CozinhaRepository;
import com.will.shop.algafoodapi.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaControle {
    @Autowired
    private CozinhaService cozinhaService;


    @GetMapping
    public ResponseEntity<List<Cozinha>> listar() {
        List<Cozinha> cozinhas = cozinhaService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(cozinhas);
    }


    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        try {
            Cozinha cozinha = cozinhaService.buscar(cozinhaId);
            return ResponseEntity.ok(cozinha);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Cozinha> salvar(@RequestBody Cozinha cozinha) {
        Cozinha obCozinha = cozinhaService.adcionar(cozinha);
        return ResponseEntity.status(HttpStatus.CREATED).body(obCozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaB = cozinhaService.buscar(cozinhaId);
        if (cozinhaB != null) {
            cozinhaService.atualizar(cozinhaId, cozinha);
            return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaB);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Void> excluir(@PathVariable Long cozinhaId) {
        try {
            cozinhaService.remover(cozinhaId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaException e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (EntitadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
