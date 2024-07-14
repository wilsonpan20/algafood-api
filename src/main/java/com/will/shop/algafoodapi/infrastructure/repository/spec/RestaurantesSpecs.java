package com.will.shop.algafoodapi.infrastructure.repository.spec;

import com.will.shop.algafoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

public class RestaurantesSpec {
    public static Specification<Restaurante> comFrenteGratis(){
        return new RestauranteComFreteGratisSpec();
    }
    public  static Specification<Restaurante> comNomeSemelhante(String nome){
        return new RestauranteComNomeSemelhanteSpec(nome);
    }
}
