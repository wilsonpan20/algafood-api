package com.will.shop.algafoodapi.api.assembler.grupoassembler;

import com.will.shop.algafoodapi.api.model.dto.request.GrupoRequestDto;

import com.will.shop.algafoodapi.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GupoRequestAssembler {
	@Autowired
	ModelMapper modelMapper;

	public Grupo requestDto(GrupoRequestDto grupoRequestDto) {
		return modelMapper.map(grupoRequestDto, Grupo.class);
	}

	public void copyToDomainObject(GrupoRequestDto grupoRequestDto, Grupo grupo) {

		modelMapper.map(grupoRequestDto, grupo);
	}
}
