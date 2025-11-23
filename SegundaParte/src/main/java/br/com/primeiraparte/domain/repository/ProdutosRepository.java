package br.com.primeiraparte.domain.repository;

import br.com.primeiraparte.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<Produto, Long> {

}
