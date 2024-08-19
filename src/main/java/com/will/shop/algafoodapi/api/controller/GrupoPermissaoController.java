package com.will.shop.algafoodapi.api.controller;

import com.will.shop.algafoodapi.api.assembler.permissaoassembler.PermissaoResponseAssembler;
import com.will.shop.algafoodapi.api.model.dto.response.PermissaoResponseDto;
import com.will.shop.algafoodapi.domain.model.Grupo;
import com.will.shop.algafoodapi.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

	@Autowired
	private GrupoService grupoService;

	@Autowired
	private PermissaoResponseAssembler permissaoResponseAssembler;

	@GetMapping
	public List<PermissaoResponseDto> listar(@PathVariable Long grupoId) {
		Grupo grupo = grupoService.buscar(grupoId);
		return permissaoResponseAssembler.toCollectionResponse(grupo.getPermissoes());
	}

	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.desassociarPermissao(grupoId, permissaoId);
	}

	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.associarPermissao(grupoId, permissaoId);
	}
}
