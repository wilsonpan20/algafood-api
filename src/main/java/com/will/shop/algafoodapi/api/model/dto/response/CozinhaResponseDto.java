package com.will.shop.algafoodapi.api.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CozinhaResponseDto {

	private Long id;
	private String nome;
}
