package br.com.primeiraparte.domain.repository;

import br.com.primeiraparte.domain.entity.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}
