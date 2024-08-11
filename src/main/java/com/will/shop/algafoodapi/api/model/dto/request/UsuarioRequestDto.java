package com.will.shop.algafoodapi.api.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UsuarioRequestDto {

	@NotBlank
	private String nome;

	@NotBlank
	@Email
	private String email;

}
