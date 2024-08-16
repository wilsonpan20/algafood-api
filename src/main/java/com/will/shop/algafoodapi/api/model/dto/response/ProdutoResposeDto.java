package com.will.shop.algafoodapi.api.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoResposeDto {

	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private Boolean ativo;

}
