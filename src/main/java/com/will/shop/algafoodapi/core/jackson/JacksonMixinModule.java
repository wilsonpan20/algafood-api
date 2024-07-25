package com.will.shop.algafoodapi.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.will.shop.algafoodapi.api.model.mixin.CidadeMixin;
import com.will.shop.algafoodapi.api.model.mixin.CozinhaMixin;
import com.will.shop.algafoodapi.api.model.mixin.RestauranteMixin;
import com.will.shop.algafoodapi.domain.model.Cidade;
import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

	public JacksonMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
		setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
	}
}
