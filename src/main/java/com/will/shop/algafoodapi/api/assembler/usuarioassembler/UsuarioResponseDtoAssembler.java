package com.will.shop.algafoodapi.api.assembler.usuarioassembler;

import com.will.shop.algafoodapi.api.model.dto.response.RestauranteResponseDto;
import com.will.shop.algafoodapi.api.model.dto.response.UsuarioResponseDto;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import com.will.shop.algafoodapi.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioResponseDtoAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public UsuarioResponseDto responseDto(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioResponseDto.class);

	}

	public List<UsuarioResponseDto> toCollectionResponse(List<Usuario> usuarios) {
		return usuarios.stream().map(this::responseDto).collect(Collectors.toList());
	}
}
