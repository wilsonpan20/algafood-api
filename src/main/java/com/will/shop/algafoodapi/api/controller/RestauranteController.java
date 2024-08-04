package com.will.shop.algafoodapi.api.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.will.shop.algafoodapi.api.assembler.RestauranteRequestDtoAssembler;
import com.will.shop.algafoodapi.api.assembler.RestauranteResponseDtoAssembler;
import com.will.shop.algafoodapi.api.model.dto.request.CozinhaRequestDto;
import com.will.shop.algafoodapi.api.model.dto.request.RestauranteRequestDto;
import com.will.shop.algafoodapi.api.model.dto.response.RestauranteResponseDto;
import com.will.shop.algafoodapi.core.validation.ValidacaoException;
import com.will.shop.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.NegocioException;
import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import com.will.shop.algafoodapi.domain.service.RestauranteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("v1/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteService restauranteService;

	@Autowired
	private SmartValidator validator;

	@Autowired
	private RestauranteResponseDtoAssembler restauranteResponseDtoAssembler;

	@Autowired
	private RestauranteRequestDtoAssembler restauranteRequestDtoAssembler;

	@GetMapping
	public List<RestauranteResponseDto> listar() {
		List<Restaurante> restaurantes = restauranteService.listar();
		return restauranteResponseDtoAssembler.toCollectionResponse(restaurantes);
	}

	@GetMapping("/{restauranteId}")
	public RestauranteResponseDto buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscar(restauranteId);
		RestauranteResponseDto restauranteResponseDto = restauranteResponseDtoAssembler.responseDto(restaurante);

		return restauranteResponseDto;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteResponseDto salvar(@RequestBody @Valid RestauranteRequestDto restaurante) {
		try {
			Restaurante restauranteRequestDto = restauranteRequestDtoAssembler.requestDto(restaurante);

			return restauranteResponseDtoAssembler.responseDto(restauranteService.adcionar(restauranteRequestDto));

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public RestauranteResponseDto atualizar(@PathVariable Long restauranteId,
			@RequestBody @Valid RestauranteRequestDto restaurante) {
		return restauranteResponseDtoAssembler.responseDto(
				restauranteService.atualizar(restauranteId, restauranteRequestDtoAssembler.requestDto(restaurante)));
	}

	@PatchMapping("/{restauranteId}")
	public RestauranteResponseDto atualizarParcial(@PathVariable Long restauranteId,
			@RequestBody @Valid Map<String, Object> campos, HttpServletRequest request) {
		Restaurante restaurante = restauranteService.buscar(restauranteId);
		merge(campos, restaurante, request);
		validate(restaurante, "restaurante");

		RestauranteRequestDto restauranteRequestDto = toDto(restaurante);

		return restauranteResponseDtoAssembler.responseDto(restauranteService.atualizar(restauranteId,
				restauranteRequestDtoAssembler.requestDto(restauranteRequestDto)));

	}

	private static void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino,
			HttpServletRequest request) {
		ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(request);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

			Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);

			camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
				if (field != null) {
					field.setAccessible(true);
					Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
					ReflectionUtils.setField(field, restauranteDestino, novoValor);
				}

			});
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletServerHttpRequest);
		}

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

	private void validate(Restaurante restaurante, String objectName) {
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);

		validator.validate(restaurante, bindingResult);

		if (bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}

	}

	private RestauranteRequestDto toDto(Restaurante restaurante) {
		RestauranteRequestDto restauranteRequestDto = new RestauranteRequestDto();
		CozinhaRequestDto cozinhaRequestDto = new CozinhaRequestDto();
		cozinhaRequestDto.setId(restaurante.getCozinha().getId());
		restauranteRequestDto.setNome(restaurante.getNome());
		restauranteRequestDto.setTaxaFrete(restaurante.getTaxaFrete());
		restauranteRequestDto.setCozinha(cozinhaRequestDto);

		return restauranteRequestDto;
	}
}
