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

    public User  findByUser(String id){
        User user = em.find(User.class, id);
        return user;
    }

    @Transactional
    public User salvar(User user){
        return em.merge(user);
    }
    @Transactional
    public void deleteUser(String id) {
        User user = em.find(User.class, id);
        if(user != null)
        em.remove(user);
    }
}
