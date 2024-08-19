package com.will.shop.algafoodapi.api.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestauranteResponseDto {

	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	private boolean ativo;
	private boolean aberto;
	private CozinhaResponseDto cozinha;
	private EnderecoResponseDto endereco;
}
