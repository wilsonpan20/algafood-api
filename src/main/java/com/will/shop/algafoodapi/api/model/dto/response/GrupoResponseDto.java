package com.will.shop.algafoodapi.api.model.dto.response;

import com.will.shop.algafoodapi.domain.model.Permissao;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GrupoResponseDto {
	private Long id;
	private String nome;
	private List<Permissao> permissoes = new ArrayList<>();
}
