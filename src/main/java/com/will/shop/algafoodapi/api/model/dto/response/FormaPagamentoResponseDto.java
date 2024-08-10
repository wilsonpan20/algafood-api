package com.will.shop.algafoodapi.api.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormaPagamentoResponseDto {

	private Long id;
	private String descricao;
}
