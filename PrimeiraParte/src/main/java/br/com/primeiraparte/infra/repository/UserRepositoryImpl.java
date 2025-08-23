package br.com.primeiraparte.infra.repository;

import br.com.primeiraparte.domain.entity.User;
import br.com.primeiraparte.domain.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User>  findByAllUser(){
        TypedQuery<User> query = em.createQuery("from User", User.class);
        List<User> users = query.getResultList();
        if(users.isEmpty()) return null;
        return users;
    }

    @Override
    public User  findByUser(String id){
        User user = em.find(User.class, id);
        return user;
    }

    @Transactional
    @Override
    public User salvar(User user){
        return em.merge(user);
    }
    @Transactional
    @Override
    public void deleteUser(String id) {
        User user = em.find(User.class, id);
        if(user != null)
            em.remove(user);
    }
}
