package com.will.shop.algafoodapi.api.assembler.formapagamentoassembler;

import com.will.shop.algafoodapi.api.model.dto.request.FormaPagamentoRequestDto;
import com.will.shop.algafoodapi.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoRequestAssembler {

	@Autowired
	ModelMapper modelMapper;

	public FormaPagamento requestDto(FormaPagamentoRequestDto formaPagamentoRequestDto) {
		return modelMapper.map(formaPagamentoRequestDto, FormaPagamento.class);
	}

	public void copyToDomainObject(FormaPagamentoRequestDto formaPagamentoRequestDto, FormaPagamento formaPagamento) {

		modelMapper.map(formaPagamentoRequestDto, formaPagamento);
	}

}
