package com.will.shop.algafoodapi.api.controller;

import com.will.shop.algafoodapi.api.assembler.produtoassembler.ProdutoRequestAssembler;
import com.will.shop.algafoodapi.api.assembler.produtoassembler.ProdutoResposeAssembler;
import com.will.shop.algafoodapi.api.model.dto.request.ProdutoRequestDto;
import com.will.shop.algafoodapi.api.model.dto.response.ProdutoResposeDto;
import com.will.shop.algafoodapi.domain.model.Produto;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import com.will.shop.algafoodapi.domain.repository.ProdutoRepository;
import com.will.shop.algafoodapi.domain.service.ProdutoService;
import com.will.shop.algafoodapi.domain.service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private RestauranteService restauranteService;
	@Autowired
	private ProdutoResposeAssembler produtoResposeAssembler;
	@Autowired
	private ProdutoRequestAssembler produtoRequestAssembler;

	@GetMapping
	public List<ProdutoResposeDto> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscar(restauranteId);

		List<Produto> todosProdutos = produtoRepository.findByRestaurante(restaurante);
		return produtoResposeAssembler.listToDTO(todosProdutos);
	}

	@GetMapping("/{produtoId}")
	public ProdutoResposeDto buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = produtoService.buscar(restauranteId, produtoId);
		return produtoResposeAssembler.resposeDto(produto);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	ProdutoResposeDto adcionar(@PathVariable Long restauranteId,
			@RequestBody @Valid ProdutoRequestDto produtoRequestDto) {

		Restaurante restaurante = restauranteService.buscar(restauranteId);
		Produto produto = produtoRequestAssembler.requestDto(produtoRequestDto);

		produto.setRestaurante(restaurante);
		produto = produtoService.salvar(produto);
		return produtoResposeAssembler.resposeDto(produto);
	}

	@PutMapping("/{produtoId}")
	public ProdutoResposeDto atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@RequestBody @Valid ProdutoRequestDto produtoRequestDto) {
		Produto produtoAtual = produtoService.buscar(restauranteId, produtoId);
		produtoRequestAssembler.copyToDomainObeject(produtoRequestDto, produtoAtual);
		produtoAtual = produtoService.salvar(produtoAtual);
		return produtoResposeAssembler.resposeDto(produtoAtual);
	}

}
