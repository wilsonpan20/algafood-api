package com.will.shop.algafoodapi.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.will.shop.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.EntitadeEmUsoException;
import com.will.shop.algafoodapi.domain.exception.NegocioException;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import com.will.shop.algafoodapi.domain.repository.RestauranteRepository;
import com.will.shop.algafoodapi.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/restaurantes")
public class RestauranteController {
    @Autowired
    RestauranteService restauranteService;

    @GetMapping
    public List<Restaurante> listar() {
        List<Restaurante> restaurantes = restauranteService.listar();
        return restaurantes;
    }

    @GetMapping("/{restauranteId}")
    public Restaurante buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscar(restauranteId);
        return restaurante;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante salvar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = restauranteService.adcionar(restaurante);
            return restaurante;

        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Restaurante atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
        return restauranteService.atualizar(restauranteId, restaurante);
    }

    @PatchMapping("/{restauranteId}")
    public Restaurante atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos) {
        Restaurante restaurante = restauranteService.buscar(restauranteId);
        merge(campos, restaurante);
        return atualizar(restauranteId, restaurante);
    }

    private static void merge(Map<String, Object> campos, Restaurante restaurante) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteMapper = objectMapper.convertValue(campos, Restaurante.class);

        campos.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field, restauranteMapper);
            ReflectionUtils.setField(field, restaurante, novoValor);

        });
    }

    private static Object convertValue(Object valorPropriedade, Class<?> targetType) {
        if (targetType.equals(BigDecimal.class) && valorPropriedade instanceof Number) {
            return BigDecimal.valueOf(((Number) valorPropriedade).doubleValue());
        }
        return valorPropriedade;
    }

    @DeleteMapping("/{restauranteId}")
    public void excluir(@PathVariable Long restauranteId) {
        restauranteService.remover(restauranteId);
    }
}
