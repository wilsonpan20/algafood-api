package com.will.shop.algafoodapi.api.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeIdRequestDto {
	@NotNull
	private Long id;
}
