package com.will.shop.algafoodapi.api.model.dto.request;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaRequestDto {

	@NotBlank
	private String nome;
}
