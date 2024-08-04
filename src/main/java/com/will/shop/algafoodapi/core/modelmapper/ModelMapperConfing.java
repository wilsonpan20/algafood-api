package com.will.shop.algafoodapi.core.modelmapper;

import com.will.shop.algafoodapi.api.model.dto.response.RestauranteResponseDto;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfing {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.createTypeMap(Restaurante.class, RestauranteResponseDto.class)
				.addMapping(Restaurante::getTaxaFrete, RestauranteResponseDto::setPrecoFrete);
		return modelMapper;
	}
}
