package br.com.primeiraparte.infra.repository;

import br.com.primeiraparte.domain.entity.Permissao;
import br.com.primeiraparte.domain.repository.PermissaoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permissao> listar() {
        return manager.createQuery("from Permissao", Permissao.class)
                .getResultList();
    }

    @Override
    public Permissao buscar(Long id) {
        return manager.find(Permissao.class, id);
    }

    @Transactional
    @Override
    public Permissao salvar(Permissao Permissao) {
        return manager.merge(Permissao);
    }

    @Transactional
    @Override
    public void remover(Permissao Permissao) {
        Permissao = buscar(Permissao.getId());
        manager.remove(Permissao);
    }

}
