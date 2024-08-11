package com.will.shop.algafoodapi.api.controller;

import com.will.shop.algafoodapi.api.assembler.grupoassembler.GrupoResponseAssenbler;
import com.will.shop.algafoodapi.api.assembler.grupoassembler.GupoRequestAssembler;
import com.will.shop.algafoodapi.api.model.dto.request.GrupoRequestDto;
import com.will.shop.algafoodapi.api.model.dto.response.GrupoResponseDto;
import com.will.shop.algafoodapi.domain.exception.GrupoNaoEncontradaException;
import com.will.shop.algafoodapi.domain.exception.NegocioException;
import com.will.shop.algafoodapi.domain.model.Grupo;
import com.will.shop.algafoodapi.domain.service.GrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/grupos")
public class GrupoControlller {

	@Autowired
	private GrupoService grupoService;

	@Autowired
	private GrupoResponseAssenbler grupoResponseAssenbler;

	@Autowired
	private GupoRequestAssembler gupoRequestAssembler;

	@GetMapping
	public List<GrupoResponseDto> listar() {
		return grupoResponseAssenbler.listToDTO(grupoService.listar());
	}

	@GetMapping("{grupoId}")
	public GrupoResponseDto buscar(@PathVariable Long grupoId) {
		return grupoResponseAssenbler.responseDto(grupoService.buscar(grupoId));
	}

	@PostMapping
	public GrupoResponseDto adcionar(@RequestBody @Valid GrupoRequestDto grupoRequestDto) {
		return grupoResponseAssenbler.responseDto(
				grupoService.adcionar(gupoRequestAssembler.requestDto(grupoRequestDto)));
	}

	@PutMapping("{grupoId}")
	GrupoResponseDto atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoRequestDto grupoRequestDto) {
		try {
			Grupo grupo = grupoService.buscar(grupoId);
			gupoRequestAssembler.copyToDomainObject(grupoRequestDto, grupo);
			return grupoResponseAssenbler.responseDto(grupoService.adcionar(grupo));
		} catch (GrupoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@DeleteMapping("{grupoId}")
	void delete(@PathVariable Long grupoId) {
		try {
			grupoService.remove(grupoId);
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException(e.getMessage());
		}
	}
}
