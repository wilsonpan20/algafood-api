package com.will.shop.algafoodapi.api.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResponseDto {

	private long id;
	private String nome;
	@JsonIgnoreProperties(value = {"name"},allowGetters = true)
	private EstadoResponseDto estado;
}
