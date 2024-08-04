package com.will.shop.algafoodapi.api.assembler.estadoassembler;

import com.will.shop.algafoodapi.api.model.dto.response.CozinhaResponseDto;
import com.will.shop.algafoodapi.api.model.dto.response.EstadoResponseDto;
import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoResponseAssembler {
	@Autowired
	private ModelMapper modelMapper;

	public EstadoResponseDto responseDto(Estado estado) {
		return modelMapper.map(estado, EstadoResponseDto.class);

	}

	public List<EstadoResponseDto> toCollectionResponse(List<Estado> estados) {
		return estados.stream().map(this::responseDto).collect(Collectors.toList());
	}
}
