package com.will.shop.algafoodapi.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.will.shop.algafoodapi.core.validation.Groups;
import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Endereco;
import com.will.shop.algafoodapi.domain.model.FormaPagamento;
import com.will.shop.algafoodapi.domain.model.Produto;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestauranteMixin {

	@JsonIgnoreProperties(value = { "name" }, allowGetters = true)
	private Cozinha cozinha;

	@JsonIgnore
	private Endereco endereco;

	@JsonIgnore
	private LocalDateTime dataCadastro;

	@JsonIgnore
	private LocalDateTime dataAtualizacao;

	@JsonIgnore
	private List<FormaPagamento> formasPagamento = new ArrayList<>();

	@JsonIgnore
	private List<Produto> produtos = new ArrayList<>();
}
