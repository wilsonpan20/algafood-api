package com.will.shop.algafoodapi.api.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequestDto {

	@NotBlank
	private String cep;
	@NotBlank
	private String logradouro;
	@NotBlank
	private String numero;
	@NotBlank
	private String complemento;
	@NotBlank
	private String bairro;
	@Valid
	@NotNull
	private CidadeIdRequestDto cidade;

}
