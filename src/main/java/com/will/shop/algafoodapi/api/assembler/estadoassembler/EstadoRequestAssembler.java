package com.will.shop.algafoodapi.api.assembler.estadoassembler;

import com.will.shop.algafoodapi.api.model.dto.request.CozinhaRequestDto;
import com.will.shop.algafoodapi.api.model.dto.request.EstadoRequestDto;
import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoRequestAssembler {
	@Autowired
	private ModelMapper modelMapper;

	public Estado requestDto(EstadoRequestDto estadoRequestDto) {
		return modelMapper.map(estadoRequestDto, Estado.class);
	}

	public void copyToDomainObject(EstadoRequestDto estadoRequestDto, Estado estado) {

		modelMapper.map(estadoRequestDto, estado);
	}
}
