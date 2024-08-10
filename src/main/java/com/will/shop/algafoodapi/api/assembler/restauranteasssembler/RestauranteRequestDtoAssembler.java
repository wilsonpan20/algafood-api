package com.will.shop.algafoodapi.api.assembler.restauranteasssembler;

import com.will.shop.algafoodapi.api.model.dto.request.RestauranteRequestDto;
import com.will.shop.algafoodapi.domain.model.Cidade;
import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Endereco;
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

	public void copyToDomainObject(RestauranteRequestDto restauranteRequestDto, Restaurante restaurante) {

		/** Para evitar essa exception identifier of an instance
		 of com.will.shop.algafoodapi.domain.model.Cozinha was altered from 2 to 1**/
		restaurante.setCozinha(new Cozinha());

		if (restaurante.getEndereco() != null) {
			restaurante.getEndereco().setCidade(new Cidade());
		}

		modelMapper.map(restauranteRequestDto, restaurante);
	}

	public RestauranteRequestDto toDto(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteRequestDto.class);
	}
}
