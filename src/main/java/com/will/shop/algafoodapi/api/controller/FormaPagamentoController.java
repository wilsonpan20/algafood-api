package com.will.shop.algafoodapi.api.controller;

import com.will.shop.algafoodapi.api.assembler.formapagamentoassembler.FormaPagamentoRequestAssembler;
import com.will.shop.algafoodapi.api.assembler.formapagamentoassembler.FormaPagamentoResponseAssembler;
import com.will.shop.algafoodapi.api.model.dto.request.FormaPagamentoRequestDto;
import com.will.shop.algafoodapi.api.model.dto.response.FormaPagamentoResponseDto;
import com.will.shop.algafoodapi.domain.exception.FormaPagamentoNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.NegocioException;
import com.will.shop.algafoodapi.domain.model.FormaPagamento;
import com.will.shop.algafoodapi.domain.service.FormaPagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/forma-pagamentos")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoService formaPagamentoService;

	@Autowired
	private FormaPagamentoRequestAssembler formaPagamentoRequestAssembler;

	@Autowired
	private FormaPagamentoResponseAssembler formaPagamentoResponseAssembler;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<FormaPagamentoResponseDto> listar() {
		return formaPagamentoResponseAssembler.listToDTO(formaPagamentoService.listar());
	}

	@GetMapping("{formaPagamentoId}")
	@ResponseStatus(HttpStatus.OK)
	FormaPagamentoResponseDto buscar(@PathVariable Long formaPagamentoId) {
		return formaPagamentoResponseAssembler.responseDto(formaPagamentoService.buscar(formaPagamentoId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	FormaPagamentoResponseDto adcionar(@RequestBody @Valid FormaPagamentoRequestDto formaPagamentoRequestDto) {
		return formaPagamentoResponseAssembler.responseDto(
				formaPagamentoService.salvar(formaPagamentoRequestAssembler.requestDto(formaPagamentoRequestDto)));
	}

	@PutMapping("{formaPagamentoId}")
	@ResponseStatus(HttpStatus.OK)
	FormaPagamentoResponseDto atualizar(@RequestBody @Valid FormaPagamentoRequestDto formaPagamentoRequestDto,
			@PathVariable Long formaPagamentoId) {
		try {
			FormaPagamento formaPagamentoExistente = formaPagamentoService.buscar(formaPagamentoId);
			formaPagamentoRequestAssembler.copyToDomainObject(formaPagamentoRequestDto, formaPagamentoExistente);
			return formaPagamentoResponseAssembler.responseDto(formaPagamentoService.salvar(formaPagamentoExistente));
		} catch (FormaPagamentoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());

		}
	}

	@DeleteMapping("{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deletar(@PathVariable Long formaPagamentoId) {
		try {
			formaPagamentoService.delete(formaPagamentoId);
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException(e.getMessage());
		}

	}
}

