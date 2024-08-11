package com.will.shop.algafoodapi.api.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.OffsetDateTime;

@Setter
@Getter
public class UsuarioResponseDto {

	private Long id;

	private String nome;

	private String email;

	private OffsetDateTime dataCadastro;
}
