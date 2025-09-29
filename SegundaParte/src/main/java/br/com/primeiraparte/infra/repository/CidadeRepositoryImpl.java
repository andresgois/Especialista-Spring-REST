package br.com.primeiraparte.infra.repository;

import br.com.primeiraparte.domain.entity.Cidade;
import br.com.primeiraparte.domain.repository.CidadeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cidade> listar() {
        return manager.createQuery("from Cidade", Cidade.class)
                .getResultList();
    }

    @Override
    public Cidade buscar(Long id) {
        return manager.find(Cidade.class, id);
    }

    @Transactional
    @Override
    public Cidade salvar(Cidade Cidade) {
        return manager.merge(Cidade);
    }

    @Transactional
    @Override
    public void remover(Cidade Cidade) {
        Cidade = buscar(Cidade.getId());
        manager.remove(Cidade);
    }

}
