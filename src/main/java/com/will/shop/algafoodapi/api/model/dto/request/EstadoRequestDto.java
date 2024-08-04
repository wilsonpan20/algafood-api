package com.will.shop.algafoodapi.api.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoRequestDto {
	@NotNull
	private Long id;

	@NotBlank
	private String nome;
}
