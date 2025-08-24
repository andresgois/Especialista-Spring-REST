package br.com.primeiraparte.domain.repository;

import br.com.primeiraparte.domain.entity.Permissao;

import java.util.List;

public interface PermissaoRepository {
    List<Permissao> listar();
    Permissao buscar(Long id);
    Permissao salvar(Permissao Permissao);
    void remover(Permissao Permissao);

}
