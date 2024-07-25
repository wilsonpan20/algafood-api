package com.will.shop.algafoodapi.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.will.shop.algafoodapi.core.validation.Groups;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

public class CozinhaMixin {
	@JsonIgnore
	private List<Restaurante> restaurantes = new ArrayList<>();
}
