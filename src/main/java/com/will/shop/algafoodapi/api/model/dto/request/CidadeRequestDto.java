package com.will.shop.algafoodapi.api.model.dto.request;

import com.will.shop.algafoodapi.domain.model.Estado;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeRequestDto {

	@NotBlank
	private String nome;

	@Valid
	@NotNull
	private EstadoIdRequestDto estado;
}
