package com.will.shop.algafoodapi.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class RestauranteResponseDto {
	private Long id;
	private  String nome;
	private BigDecimal taxaFrete;
	private CozinhaResponseDto cozinha;

}
