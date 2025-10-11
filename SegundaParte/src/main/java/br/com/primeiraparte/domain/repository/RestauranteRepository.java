package br.com.primeiraparte.domain.repository;

import br.com.primeiraparte.domain.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

    boolean existsByNome(String nome);
    Optional<Restaurante> findByNome(String nome);
    Optional<Restaurante> findById(Long id);
    Optional<Restaurante> findByNomeContaining(String nome);
    List<Restaurante> findAllByNomeContaining(String nome);
    List<Restaurante> findTop3ByNomeContaining(String nome);
    int countByNomeContaining(String nome);

    // query sera chamada no orm.xml
    //@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
    List<Restaurante> consultarPorNome(@Param("nome") String nome, @Param("id") String id);
}
