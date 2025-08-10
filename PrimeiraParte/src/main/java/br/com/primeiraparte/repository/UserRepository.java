package br.com.primeiraparte.repository;

import br.com.primeiraparte.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<Object> findById(UUID id);
}
