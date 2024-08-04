package com.will.shop.algafoodapi.api.assembler.restauranteasssembler;

import com.will.shop.algafoodapi.api.model.dto.response.RestauranteResponseDto;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteResponseDtoAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public RestauranteResponseDto responseDto(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteResponseDto.class);

	}

	public List<RestauranteResponseDto> toCollectionResponse(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(this::responseDto).collect(Collectors.toList());
	}
}
