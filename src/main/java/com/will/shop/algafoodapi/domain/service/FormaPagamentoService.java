package com.will.shop.algafoodapi.domain.service;

import com.will.shop.algafoodapi.domain.model.FormaPagamento;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FormaPagamentoService {

	List<FormaPagamento> listar();

	FormaPagamento buscar(Long formaPagamentoId);

	FormaPagamento salvar(FormaPagamento formaPagamento);

	void delete(Long formaPagamentoId);

}
