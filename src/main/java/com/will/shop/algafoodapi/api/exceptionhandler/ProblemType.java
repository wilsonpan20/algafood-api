package com.will.shop.algafoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    PARAMENTRO_INVALIDO("paramentro-invalido","paramentro passado errado"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel","Mensagem incompreensivel"),
    RECURSO_NAO_ENCONTRADA("/recurso-nao-encontrada", "Recurso não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_DE_NEGOCIO("/erro-de-negocio", "Violação de regra de negocio");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}
