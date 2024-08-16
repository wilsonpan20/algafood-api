package com.will.shop.algafoodapi.api.assembler.produtoassembler;

import com.will.shop.algafoodapi.api.model.dto.response.ProdutoResposeDto;
import com.will.shop.algafoodapi.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoResposeAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public ProdutoResposeDto resposeDto(Produto produto) {
		return modelMapper.map(produto, ProdutoResposeDto.class);
	}

	public List<ProdutoResposeDto> listToDTO(List<Produto> produtos) {
		return produtos.stream().map(this::resposeDto).collect(Collectors.toList());
	}
}
