package com.will.shop.algafoodapi.api.assembler.cidadeassembler;

import com.will.shop.algafoodapi.api.model.dto.request.CidadeRequestDto;
import com.will.shop.algafoodapi.api.model.dto.request.RestauranteRequestDto;
import com.will.shop.algafoodapi.domain.model.Cidade;
import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Estado;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeRequestDtoAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public Cidade requestDto(CidadeRequestDto cidadeRequestDto) {
		return modelMapper.map(cidadeRequestDto, Cidade.class);
	}

	public void copyToDomainObject(CidadeRequestDto cidadeRequestDto, Cidade cidade) {

		/** Para evitar essa exception identifier of an instance
		 of com.will.shop.algafoodapi.domain.model.Cozinha was altered from 2 to 1**/
		cidade.setEstado(new Estado());

		modelMapper.map(cidadeRequestDto, cidade);
	}

}
