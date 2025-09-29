package br.com.primeiraparte.infra.repository;

import br.com.primeiraparte.domain.entity.Estado;
import br.com.primeiraparte.domain.repository.EstadoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> listar() {
        return manager.createQuery("from Estado", Estado.class)
                .getResultList();
    }

    @Override
    public Estado buscar(Long id) {
        return manager.find(Estado.class, id);
    }

    @Transactional
    @Override
    public Estado salvar(Estado Estado) {
        return manager.merge(Estado);
    }

    @Transactional
    @Override
    public void remover(Estado Estado) {
        Estado = buscar(Estado.getId());
        manager.remove(Estado);
    }

}
