package com.will.shop.algafoodapi.core.modelmapper;

import com.will.shop.algafoodapi.api.model.dto.response.EnderecoResponseDto;
import com.will.shop.algafoodapi.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {

		var modelMapper = new ModelMapper();

		var enderecoToEnderecoResponseTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoResponseDto.class);
		enderecoToEnderecoResponseTypeMap.<String>addMapping(
				enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				(dest, value) -> dest.getCidade().setEstado(value));
		return modelMapper;
	}
}
