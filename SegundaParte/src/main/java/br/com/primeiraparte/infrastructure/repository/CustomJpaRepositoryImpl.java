package br.com.primeiraparte.infrastructure.repository;

import br.com.primeiraparte.domain.repository.CustomJpaRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>
        implements CustomJpaRepository<T, ID> {
    
    private EntityManager entityManager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> buscarPrimeiro() {
        String jpql = "from " + getDomainClass().getName();
        T entity = (T) entityManager.createQuery(jpql, getDomainClass())
                .setMaxResults(1)
                .getSingleResult();
        return Optional.ofNullable(entity);
    }
}
