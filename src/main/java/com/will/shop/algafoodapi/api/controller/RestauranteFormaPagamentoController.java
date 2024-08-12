package com.will.shop.algafoodapi.api.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.will.shop.algafoodapi.api.assembler.formapagamentoassembler.FormaPagamentoResponseAssembler;
import com.will.shop.algafoodapi.api.assembler.restauranteasssembler.RestauranteRequestDtoAssembler;
import com.will.shop.algafoodapi.api.assembler.restauranteasssembler.RestauranteResponseDtoAssembler;
import com.will.shop.algafoodapi.api.model.dto.request.RestauranteRequestDto;
import com.will.shop.algafoodapi.api.model.dto.response.FormaPagamentoResponseDto;
import com.will.shop.algafoodapi.api.model.dto.response.RestauranteResponseDto;
import com.will.shop.algafoodapi.core.validation.ValidacaoException;
import com.will.shop.algafoodapi.domain.exception.CidadeNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.NegocioException;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import com.will.shop.algafoodapi.domain.service.RestauranteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

	@Autowired
	private RestauranteService restauranteService;

	@Autowired
	FormaPagamentoResponseAssembler formaPagamentoResponseAssembler;

	@GetMapping
	public List<FormaPagamentoResponseDto> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscar(restauranteId);
		return formaPagamentoResponseAssembler.listToDTO(restaurante.getFormasPagamento());
	}

	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);

	}

	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteService.adcionarFormaPagamento(restauranteId, formaPagamentoId);

	}
}
