package com.will.shop.algafoodapi.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {ValorZeroIncluiDescricaoValidator.class})
public @interface ValorZeroIncluirDescricao {
    String message() default "descricao obrigatoria invalida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default  {};

    String valorField();
    String descricaoField();
    String descricaoObrigatoria();


}
