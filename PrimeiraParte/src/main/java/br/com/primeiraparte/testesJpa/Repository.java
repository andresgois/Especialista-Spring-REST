package br.com.primeiraparte.testesJpa;

import br.com.primeiraparte.domain.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class Repository {

    @PersistenceContext
    private EntityManager em;

    public List<User>  findByAllUser(){
        TypedQuery<User> query = em.createQuery("from User", User.class);
        List<User> users = query.getResultList();
        if(users.isEmpty()) return null;
        return users;
    }

    @Transactional
    public User createUser(User user){
        return em.merge(user);
    }
}
