package com.will.shop.algafoodapi.api.assembler;

import com.will.shop.algafoodapi.api.model.dto.request.RestauranteRequestDto;
import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteRequestDtoAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public Restaurante requestDto(RestauranteRequestDto restauranteRequestDto) {
		return modelMapper.map(restauranteRequestDto, Restaurante.class);
	}
}
