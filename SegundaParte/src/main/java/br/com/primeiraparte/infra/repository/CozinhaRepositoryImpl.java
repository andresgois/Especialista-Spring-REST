package br.com.primeiraparte.infra.repository;

import br.com.primeiraparte.domain.entity.Cozinha;
import br.com.primeiraparte.domain.repository.CozinhaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar() {
        return manager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }

    @Override
    public Cozinha buscar(Long id) {
        return manager.find(Cozinha.class, id);
    }

    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

    @Transactional
    @Override
    public void remover(Cozinha cozinha) {
        cozinha = buscar(cozinha.getId());
        manager.remove(cozinha);
    }

    @Override
    public List<Cozinha> buscar(String nome) {
        List<Cozinha>  cozinha = manager.createQuery("from Cozinha where upper(nome) like upper(:nome) ")
                .setParameter("nome", "%" + nome.toUpperCase() + "%")
                .getResultList();
        return cozinha;
    }

}
