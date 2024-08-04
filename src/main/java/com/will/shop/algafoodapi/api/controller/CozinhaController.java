package com.will.shop.algafoodapi.api.controller;

import com.will.shop.algafoodapi.api.assembler.cozinhaassembler.CozinhaRequestAssembler;
import com.will.shop.algafoodapi.api.assembler.cozinhaassembler.CozinhaResponseAssembler;
import com.will.shop.algafoodapi.api.model.dto.request.CozinhaRequestDto;
import com.will.shop.algafoodapi.api.model.dto.response.CozinhaResponseDto;
import com.will.shop.algafoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.NegocioException;
import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.service.CozinhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaService cozinhaService;

	@Autowired
	private CozinhaResponseAssembler cozinhaResponseAssembler;

	@Autowired
	private CozinhaRequestAssembler cozinhaRequestAssembler;

	@GetMapping
	public ResponseEntity<List<CozinhaResponseDto>> listar() {
		List<CozinhaResponseDto> cozinhas = cozinhaResponseAssembler.toCollectionResponse(cozinhaService.listar());
		return ResponseEntity.status(HttpStatus.OK).body(cozinhas);
	}

	@GetMapping("/{cozinhaId}")
	public CozinhaResponseDto buscar(@PathVariable Long cozinhaId) {
		return cozinhaResponseAssembler.responseDto(cozinhaService.buscar(cozinhaId));
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public CozinhaResponseDto salvar(@RequestBody @Valid CozinhaRequestDto cozinhaRequestDto) {
		return cozinhaResponseAssembler.responseDto(
				cozinhaService.adcionar(cozinhaRequestAssembler.requestDto(cozinhaRequestDto)));
	}

	@PutMapping("/{cozinhaId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public CozinhaResponseDto atualizar(@PathVariable Long cozinhaId,
			@RequestBody CozinhaRequestDto cozinhaRequestDto) {
		try {
			Cozinha cozinhaExistente = cozinhaService.buscar(cozinhaId);
			cozinhaRequestAssembler.copyToDomainObject(cozinhaRequestDto, cozinhaExistente);
			return cozinhaResponseAssembler.responseDto(cozinhaService.adcionar(cozinhaExistente));
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long cozinhaId) {
		try {
			cozinhaService.remover(cozinhaId);
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException(e.getMessage());
		}

	}
}
