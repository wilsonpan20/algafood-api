package com.will.shop.algafoodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Produto {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;


    @NotBlank
    @Column(nullable = false)
    private String descricao;


    @NotBlank
    @Column(nullable = false)
    private BigDecimal preco;

    @NotBlank
    @Column(nullable = false)
    private Boolean ativo;

    @NotBlank
    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurante restaurante;

}
