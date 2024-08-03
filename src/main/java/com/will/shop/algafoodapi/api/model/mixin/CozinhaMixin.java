package com.will.shop.algafoodapi.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.will.shop.algafoodapi.domain.model.Restaurante;


import java.util.ArrayList;
import java.util.List;

public class CozinhaMixin {
	@JsonIgnore
	private List<Restaurante> restaurantes = new ArrayList<>();
}
