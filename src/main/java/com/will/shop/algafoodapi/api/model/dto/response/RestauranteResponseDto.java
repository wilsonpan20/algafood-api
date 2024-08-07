package com.will.shop.algafoodapi.api.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RestauranteResponseDto {

	private Long id;

	private String nome;

	private BigDecimal precoFrete;

	private CozinhaResponseDto cozinha;

	private boolean ativo;
}
