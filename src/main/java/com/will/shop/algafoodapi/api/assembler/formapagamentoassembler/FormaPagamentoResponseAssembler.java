package com.will.shop.algafoodapi.api.assembler.formapagamentoassembler;

import com.will.shop.algafoodapi.api.model.dto.response.FormaPagamentoResponseDto;
import com.will.shop.algafoodapi.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoResponseAssembler {

	@Autowired
	ModelMapper modelMapper;

	public FormaPagamentoResponseDto responseDto(FormaPagamento formaPagamento) {

		return modelMapper.map(formaPagamento, FormaPagamentoResponseDto.class);
	}

	public List<FormaPagamentoResponseDto> listToDTO(Collection<FormaPagamento> formaPagamentos) {

		return formaPagamentos.stream().map(this::responseDto).collect(Collectors.toList());
	}

}
