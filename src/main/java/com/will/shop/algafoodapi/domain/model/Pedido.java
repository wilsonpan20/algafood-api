package com.will.shop.algafoodapi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String codigo;

	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;

	@Embedded
	private Endereco enderecoEntrega;

	private StatusPedido status;

	@NotBlank
	@CreationTimestamp
	@Column(name = "data_criacao", nullable = false)
	private OffsetDateTime dataCriacao;

	private OffsetDateTime dataEntrega;

	private OffsetDateTime dataCancelamento;

	@NotBlank
	@ManyToOne
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;

	@NotBlank
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;

	@NotBlank
	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario cliente;

	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens = new ArrayList<>();

}
