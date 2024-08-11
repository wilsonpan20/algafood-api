package com.will.shop.algafoodapi.api.assembler.grupoassembler;

import com.will.shop.algafoodapi.api.model.dto.response.FormaPagamentoResponseDto;
import com.will.shop.algafoodapi.api.model.dto.response.GrupoResponseDto;
import com.will.shop.algafoodapi.domain.model.FormaPagamento;
import com.will.shop.algafoodapi.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoResponseAssenbler {

	@Autowired
	ModelMapper modelMapper;

	public GrupoResponseDto responseDto(Grupo grupo) {

		return modelMapper.map(grupo, GrupoResponseDto.class);
	}

	public List<GrupoResponseDto> listToDTO(List<Grupo> grupos) {

		return grupos.stream().map(this::responseDto).collect(Collectors.toList());
	}
}
