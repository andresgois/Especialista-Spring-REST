package br.com.primeiraparte.domain.repository;


import br.com.primeiraparte.domain.entity.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
    List<Cozinha> findCozinhaByNomeContaining(String nome);
}
