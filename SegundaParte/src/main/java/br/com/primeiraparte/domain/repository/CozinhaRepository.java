package br.com.primeiraparte.domain.repository;


import br.com.primeiraparte.domain.entity.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
    //List<Cozinha> buscar(String nome);
}
