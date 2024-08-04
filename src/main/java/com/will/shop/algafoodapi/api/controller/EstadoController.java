package com.will.shop.algafoodapi.api.controller;

import com.will.shop.algafoodapi.api.assembler.estadoassembler.EstadoRequestAssembler;
import com.will.shop.algafoodapi.api.assembler.estadoassembler.EstadoResponseAssembler;
import com.will.shop.algafoodapi.api.model.dto.request.EstadoRequestDto;
import com.will.shop.algafoodapi.api.model.dto.response.EstadoResponseDto;
import com.will.shop.algafoodapi.domain.exception.EstadoNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.NegocioException;
import com.will.shop.algafoodapi.domain.model.Estado;
import com.will.shop.algafoodapi.domain.service.EstadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/estados")
public class EstadoController {

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private EstadoResponseAssembler estadoResponseAssembler;

	@Autowired
	private EstadoRequestAssembler estadoRequestAssembler;

	@GetMapping
	List<EstadoResponseDto> listar() {
		List<EstadoResponseDto> estados = estadoResponseAssembler.toCollectionResponse(estadoService.listar());
		return estados;
	}

	@GetMapping("/{estadoId}")
	EstadoResponseDto buscar(@PathVariable Long estadoId) {
		EstadoResponseDto estado = estadoResponseAssembler.responseDto(estadoService.buscar(estadoId));
		return estado;
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	EstadoResponseDto adcionar(@RequestBody @Valid EstadoRequestDto estadoRequestDto) {
		return estadoResponseAssembler.responseDto(
				estadoService.adcionar(estadoRequestAssembler.requestDto(estadoRequestDto)));

	}

	@PutMapping("/{estadoId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	EstadoResponseDto altualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoRequestDto estadoRequestDto) {
		try {
			Estado estadoExistente = estadoService.buscar(estadoId);
			estadoRequestAssembler.copyToDomainObject(estadoRequestDto, estadoExistente);
			return estadoResponseAssembler.responseDto(estadoService.adcionar(estadoExistente));
		} catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@DeleteMapping("/{estadoId}")
	public void excluir(@PathVariable Long estadoId) {
		try {
			estadoService.remover(estadoId);
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException(e.getMessage());
		}

	}
}
