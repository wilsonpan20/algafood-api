package com.will.shop.algafoodapi.api.controller;

import com.will.shop.algafoodapi.api.assembler.cidadeassembler.CidadeRequestDtoAssembler;
import com.will.shop.algafoodapi.api.assembler.cidadeassembler.CidadeResponseDtoAssembler;
import com.will.shop.algafoodapi.api.model.dto.request.CidadeRequestDto;
import com.will.shop.algafoodapi.api.model.dto.response.CidadeResponseDto;
import com.will.shop.algafoodapi.domain.exception.CidadeNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.NegocioException;
import com.will.shop.algafoodapi.domain.model.Cidade;
import com.will.shop.algafoodapi.domain.service.CidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;

	@Autowired
	private CidadeResponseDtoAssembler cidadeResponseDtoAssembler;

	@Autowired
	private CidadeRequestDtoAssembler cidadeRequestDtoAssembler;

	@GetMapping
	List<CidadeResponseDto> listar() {
		List<CidadeResponseDto> cidades = cidadeResponseDtoAssembler.toCollectionResponse(cidadeService.listar());
		return cidades;
	}

	@GetMapping("/{cidadeId}")
	CidadeResponseDto buscar(@PathVariable Long cidadeId) {
		return cidadeResponseDtoAssembler.responseDto(cidadeService.buscar(cidadeId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	CidadeResponseDto adcionar(@RequestBody @Valid CidadeRequestDto cidadeRequestDto) {
		try {
			return cidadeResponseDtoAssembler.responseDto(
					cidadeService.adcionar(cidadeRequestDtoAssembler.requestDto(cidadeRequestDto)));

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{cidadeId}")
	CidadeResponseDto atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeRequestDto cidadeRequestDto) {
		try {
			Cidade cidadeExistente = cidadeService.buscar(cidadeId);

			cidadeRequestDtoAssembler.copyToDomainObject(cidadeRequestDto, cidadeExistente);
			return cidadeResponseDtoAssembler.responseDto(cidadeService.adcionar(cidadeExistente));

		} catch (CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}

	}

	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long cidadeId) {
		try {
			cidadeService.remover(cidadeId);
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException(e.getMessage());
		}
	}
}
