package com.will.shop.algafoodapi.api.assembler.cozinhaassembler;

import com.will.shop.algafoodapi.api.model.dto.request.CozinhaRequestDto;

import com.will.shop.algafoodapi.domain.model.Cozinha;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaRequestAssembler {
	@Autowired
	private ModelMapper modelMapper;

	public Cozinha requestDto(CozinhaRequestDto cozinhaRequestDto) {
		return modelMapper.map(cozinhaRequestDto, Cozinha.class);
	}

	public void copyToDomainObject(CozinhaRequestDto cozinhaRequestDto, Cozinha cozinha) {

		modelMapper.map(cozinhaRequestDto, cozinha);
	}
}
