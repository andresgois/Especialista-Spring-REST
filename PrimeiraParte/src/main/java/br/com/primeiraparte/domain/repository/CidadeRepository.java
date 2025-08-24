package br.com.primeiraparte.domain.repository;

import br.com.primeiraparte.domain.entity.Cidade;

import java.util.List;

public interface CidadeRepository {
    List<Cidade> listar();
    Cidade buscar(Long id);
    Cidade salvar(Cidade Cidade);
    void remover(Cidade Cidade);

}
