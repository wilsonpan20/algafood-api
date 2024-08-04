package com.will.shop.algafoodapi.api.assembler.cozinhaassembler;

import com.will.shop.algafoodapi.api.model.dto.response.CozinhaResponseDto;
import com.will.shop.algafoodapi.api.model.dto.response.RestauranteResponseDto;
import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaResponseAssembler {
	@Autowired
	private ModelMapper modelMapper;

	public CozinhaResponseDto responseDto(Cozinha cozinha) {
		return modelMapper.map(cozinha, CozinhaResponseDto.class);

	}

	public List<CozinhaResponseDto> toCollectionResponse(List<Cozinha> cozinhas) {
		return cozinhas.stream().map(this::responseDto).collect(Collectors.toList());
	}
}
