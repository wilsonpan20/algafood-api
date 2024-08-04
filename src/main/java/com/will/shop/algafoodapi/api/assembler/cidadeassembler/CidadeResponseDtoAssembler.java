package com.will.shop.algafoodapi.api.assembler.cidadeassembler;

import com.will.shop.algafoodapi.api.model.dto.response.CidadeResponseDto;
import com.will.shop.algafoodapi.api.model.dto.response.RestauranteResponseDto;
import com.will.shop.algafoodapi.domain.model.Cidade;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeResponseDtoAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public CidadeResponseDto responseDto(Cidade cidade) {
		return modelMapper.map(cidade, CidadeResponseDto.class);

	}

	public List<CidadeResponseDto> toCollectionResponse(List<Cidade> cidades) {
		return cidades.stream().map(this::responseDto).collect(Collectors.toList());
	}
}
