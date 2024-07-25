package com.will.shop.algafoodapi.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.will.shop.algafoodapi.core.validation.Groups;
import com.will.shop.algafoodapi.domain.model.Estado;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;

public class CidadeMixin {

	@JsonIgnoreProperties(value = { "name" }, allowGetters = true)
	private Estado estado;
}
