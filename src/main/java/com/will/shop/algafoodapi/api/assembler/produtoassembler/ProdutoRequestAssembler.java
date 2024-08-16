package com.will.shop.algafoodapi.api.assembler.produtoassembler;

import com.will.shop.algafoodapi.api.model.dto.request.ProdutoRequestDto;
import com.will.shop.algafoodapi.api.model.dto.response.ProdutoResposeDto;
import com.will.shop.algafoodapi.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoRequestAssembler {
	@Autowired
	private ModelMapper modelMapper;

	public Produto requestDto(ProdutoRequestDto produtoRequestDto) {
		return modelMapper.map(produtoRequestDto, Produto.class);
	}

	public void copyToDomainObeject(ProdutoRequestDto produtoRequestDto, Produto produto) {
		modelMapper.map(produtoRequestDto, produto);
	}

}
