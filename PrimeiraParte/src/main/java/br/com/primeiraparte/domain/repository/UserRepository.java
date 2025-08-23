package br.com.primeiraparte.domain.repository;

import br.com.primeiraparte.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    public List<User> findByAllUser();

    public User  findByUser(String id);

    public User salvar(User user);

    public void deleteUser(String id) ;
}
