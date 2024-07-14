package com.will.shop.algafoodapi.domain.service.impl;

import com.will.shop.algafoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.EntitadeEmUsoException;
import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.repository.CozinhaRepository;
import com.will.shop.algafoodapi.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CozinhaServiceImpl implements CozinhaService {

    private CozinhaRepository cozinhaRepository;

    @Autowired
    public CozinhaServiceImpl(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    @Override
    public List<Cozinha> listar() {
        List<Cozinha> cozinhas = cozinhaRepository.findAll();

        return cozinhas;
    }

    @Override
    public Cozinha buscar(long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(Cozinha.class,cozinhaId));
    }

    @Override
    public Cozinha adcionar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }


    public Cozinha atualizar(long cozinhaId, Cozinha cozinha) {

        Cozinha cozinha1 = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(Cozinha.class,cozinhaId));

        BeanUtils.copyProperties(cozinha, cozinha1, "id");

        cozinhaRepository.save(cozinha1);

        return cozinha1;
    }

    @Override
    public void remover(Long cozinhaId) {
        try {
            cozinhaRepository.deleteById(cozinhaId);

        } catch (EmptyResultDataAccessException e) {
            throw new CozinhaNaoEncontradaException(Cozinha.class,cozinhaId);

        } catch (DataIntegrityViolationException e) {
            throw new EntitadeEmUsoException (Cozinha.class, cozinhaId);
        }
    }
}
