package com.will.shop.algafoodapi.api.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.will.shop.algafoodapi.api.model.dto.request.RestauranteRequestDto;
import com.will.shop.algafoodapi.api.model.dto.response.CozinhaResponseDto;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/restaurantes")
public class RestauranteController {

	@Autowired
	RestauranteService restauranteService;

	@Autowired
	SmartValidator validator;

	@GetMapping
	public List<RestauranteResponseDto> listar() {
		List<Restaurante> restaurantes = restauranteService.listar();
		return toCollectionResponse(restaurantes);
	}

	@GetMapping("/{restauranteId}")
	public RestauranteResponseDto buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscar(restauranteId);
		RestauranteResponseDto restauranteResponseDto = responseDto(restaurante);

		return restauranteResponseDto;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteResponseDto salvar(@RequestBody @Valid RestauranteRequestDto restaurante) {
		try {
			Restaurante restauranteRequestDto = requestDto(restaurante);

			return responseDto(restauranteService.adcionar(restauranteRequestDto));

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public RestauranteResponseDto atualizar(@PathVariable Long restauranteId,
			@RequestBody @Valid Restaurante restaurante) {
		return responseDto(restauranteService.atualizar(restauranteId, restaurante));
	}

	@PatchMapping("/{restauranteId}")
	public RestauranteResponseDto atualizarParcial(@PathVariable Long restauranteId,
			@RequestBody @Valid Map<String, Object> campos, HttpServletRequest request) {
		Restaurante restaurante = restauranteService.buscar(restauranteId);
		merge(campos, restaurante, request);
		validate(restaurante, "restaurante");
		return atualizar(restauranteId, restaurante);
	}

	private static void merge(Map<String, Object> campos, Restaurante restaurante, HttpServletRequest request) {
		ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(request);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

			Restaurante restauranteMapper = objectMapper.convertValue(campos, Restaurante.class);

			campos.forEach((nomePropriedade, valorPropriedade) -> {
				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
				field.setAccessible(true);
				Object novoValor = ReflectionUtils.getField(field, restauranteMapper);
				ReflectionUtils.setField(field, restaurante, novoValor);

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

	private RestauranteResponseDto responseDto(Restaurante restaurante) {

		CozinhaResponseDto cozinhaResponseDto = new CozinhaResponseDto();
		RestauranteResponseDto restauranteResponseDto = new RestauranteResponseDto();

		cozinhaResponseDto.setId(restaurante.getCozinha().getId());
		cozinhaResponseDto.setNome(restaurante.getCozinha().getNome());
		restauranteResponseDto.setId(restaurante.getId());
		restauranteResponseDto.setNome(restaurante.getNome());
		restauranteResponseDto.setTaxaFrete(restaurante.getTaxaFrete());
		restauranteResponseDto.setCozinha(cozinhaResponseDto);

		return restauranteResponseDto;
	}

	private List<RestauranteResponseDto> toCollectionResponse(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(restaurante -> responseDto(restaurante)).collect(Collectors.toList());
	}

	private Restaurante requestDto(RestauranteRequestDto restauranteRequestDto) {
		System.out.println(restauranteRequestDto);
		Restaurante restaurante = new Restaurante();
		Cozinha cozinha = new Cozinha();
		restaurante.setNome(restauranteRequestDto.getNome());
		restaurante.setTaxaFrete(restauranteRequestDto.getTaxaFrete());
		cozinha.setId(restauranteRequestDto.getCozinha().getId());
		restaurante.setCozinha(cozinha);
		return restaurante;
	}
}
