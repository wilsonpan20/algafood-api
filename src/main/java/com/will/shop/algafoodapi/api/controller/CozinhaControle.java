package com.will.shop.algafoodapi.api.controller;

import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/cozinhas")
public class CozinhaControle {
    @Autowired
    private CozinhaRepository cozinhaRepository;
    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaRepository.listar();

    }
}
