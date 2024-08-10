package com.will.shop.algafoodapi.api.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoResponseDto {

	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private CidadeResponseDto cidade;
}
