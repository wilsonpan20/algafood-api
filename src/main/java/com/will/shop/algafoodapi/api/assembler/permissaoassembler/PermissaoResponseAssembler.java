package com.will.shop.algafoodapi.api.assembler.permissaoassembler;

import com.will.shop.algafoodapi.api.model.dto.response.PermissaoResponseDto;

import com.will.shop.algafoodapi.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissaoResponseAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public PermissaoResponseDto responseDto(Permissao permissao) {
		return modelMapper.map(permissao, PermissaoResponseDto.class);

	}

	public List<PermissaoResponseDto> toCollectionResponse(Collection<Permissao> permissoes) {
		return permissoes.stream().map(this::responseDto).collect(Collectors.toList());
	}

}
