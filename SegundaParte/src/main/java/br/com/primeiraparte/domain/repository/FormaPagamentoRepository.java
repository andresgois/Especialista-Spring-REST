package br.com.primeiraparte.domain.repository;

import br.com.primeiraparte.domain.entity.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

}
