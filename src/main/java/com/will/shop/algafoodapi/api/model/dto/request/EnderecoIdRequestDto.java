package com.will.shop.algafoodapi.api.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoIdRequestDto {

	@NotNull
	private Long id;
}
