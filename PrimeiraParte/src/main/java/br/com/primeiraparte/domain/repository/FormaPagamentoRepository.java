package br.com.primeiraparte.domain.repository;

import br.com.primeiraparte.domain.entity.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {
    List<FormaPagamento> listar();
    FormaPagamento buscar(Long id);
    FormaPagamento salvar(FormaPagamento FormaPagamento);
    void remover(FormaPagamento FormaPagamento);

}
