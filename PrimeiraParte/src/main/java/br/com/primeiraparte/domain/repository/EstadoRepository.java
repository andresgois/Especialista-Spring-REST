package br.com.primeiraparte.domain.repository;

import br.com.primeiraparte.domain.entity.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> listar();
    Estado buscar(Long id);
    Estado salvar(Estado Estado);
    void remover(Estado Estado);

}
