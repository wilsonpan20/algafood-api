package com.will.shop.algafoodapi.api.controller;

import com.will.shop.algafoodapi.api.assembler.usuarioassembler.UsuarioRequestDtoAssembler;
import com.will.shop.algafoodapi.api.assembler.usuarioassembler.UsuarioResponseDtoAssembler;
import com.will.shop.algafoodapi.api.model.dto.request.SenhaRquestDto;
import com.will.shop.algafoodapi.api.model.dto.request.UsuarioComSenhaRequestDto;
import com.will.shop.algafoodapi.api.model.dto.request.UsuarioRequestDto;
import com.will.shop.algafoodapi.api.model.dto.response.UsuarioResponseDto;
import com.will.shop.algafoodapi.domain.exception.NegocioException;
import com.will.shop.algafoodapi.domain.exception.UsuarioNaoEncontradaException;
import com.will.shop.algafoodapi.domain.model.Usuario;
import com.will.shop.algafoodapi.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRequestDtoAssembler usuarioRequestDtoAssembler;

	@Autowired
	private UsuarioResponseDtoAssembler usuarioResponseDtoAssembler;

	@GetMapping
	List<UsuarioResponseDto> listar() {
		return usuarioResponseDtoAssembler.toCollectionResponse(usuarioService.listar());
	}

	@GetMapping("{usuarioId}")
	UsuarioResponseDto buscar(@PathVariable Long usuarioId) {
		return usuarioResponseDtoAssembler.responseDto(usuarioService.buscar(usuarioId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	UsuarioResponseDto adcionar(@RequestBody @Valid UsuarioComSenhaRequestDto usuarioComSenhaRequestDto) {
		return usuarioResponseDtoAssembler.responseDto(
				usuarioService.adcionar(usuarioRequestDtoAssembler.requestDto(usuarioComSenhaRequestDto)));
	}

	@PutMapping("{usuarioId}")
	UsuarioResponseDto atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioRequestDto usuarioRequestDto) {
		try {
			Usuario usuario = usuarioService.buscar(usuarioId);
			usuarioRequestDtoAssembler.copyToDomainObject(usuarioRequestDto, usuario);
			return usuarioResponseDtoAssembler.responseDto(usuarioService.adcionar(usuario));
		} catch (UsuarioNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaRquestDto senha) {
		usuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());

	}

	@DeleteMapping("{usuarioId}")
	void deletar(@PathVariable Long usuarioId) {
		try {
			usuarioService.remove(usuarioId);
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException(e.getMessage());
		}

	}
}
