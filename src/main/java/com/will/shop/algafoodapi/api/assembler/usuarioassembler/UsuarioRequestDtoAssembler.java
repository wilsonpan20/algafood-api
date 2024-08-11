package com.will.shop.algafoodapi.api.assembler.usuarioassembler;


import com.will.shop.algafoodapi.api.model.dto.request.UsuarioRequestDto;

import com.will.shop.algafoodapi.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRequestDtoAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public Usuario requestDto(UsuarioRequestDto usuarioRequestDto) {
		return modelMapper.map(usuarioRequestDto, Usuario.class);
	}

	public void copyToDomainObject(UsuarioRequestDto usuarioRequestDto, Usuario usuario) {
		modelMapper.map(usuarioRequestDto, usuario);

	}
}
