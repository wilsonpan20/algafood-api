package com.will.shop.algafoodapi.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.will.shop.algafoodapi.domain.model.Estado;

public class CidadeMixin {

	@JsonIgnoreProperties(value = { "name" }, allowGetters = true)
	private Estado estado;
}
