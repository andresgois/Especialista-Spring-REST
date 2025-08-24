package br.com.primeiraparte.infra.repository;

import br.com.primeiraparte.domain.entity.FormaPagamento;
import br.com.primeiraparte.domain.repository.FormaPagamentoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<FormaPagamento> listar() {
        return manager.createQuery("from FormaPagamento", FormaPagamento.class)
                .getResultList();
    }

    @Override
    public FormaPagamento buscar(Long id) {
        return manager.find(FormaPagamento.class, id);
    }

    @Transactional
    @Override
    public FormaPagamento salvar(FormaPagamento FormaPagamento) {
        return manager.merge(FormaPagamento);
    }

    @Transactional
    @Override
    public void remover(FormaPagamento FormaPagamento) {
        FormaPagamento = buscar(FormaPagamento.getId());
        manager.remove(FormaPagamento);
    }

}
