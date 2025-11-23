package br.com.primeiraparte.service;

import br.com.primeiraparte.domain.entity.Produto;
import br.com.primeiraparte.domain.exception.EntidadeEmUsoException;
import br.com.primeiraparte.domain.exception.EntidadeNaoEncontrada;
import br.com.primeiraparte.domain.repository.ProdutosRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutosRepository produtosRepository;

    public List<Produto> listar() {
        return produtosRepository.findAll();
    }

    public Produto buscar(Long id) {
        return produtosRepository.findById(id).orElse(null);
    }

    public Produto salvar(Produto produto) {
        return produtosRepository.save(produto);
    }

    public Produto atualizar(Long id,Produto produto) {
        Produto produtoAtual = this.buscar(id);
        if(produto != null)
            BeanUtils.copyProperties(produto, produtoAtual, "id");
        return this.salvar(produtoAtual);
    }

    public void deletar(Long id) {
        Produto produto = null;
        try {
            produto = this.buscar(id);
            produtosRepository.delete(produto);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontrada(
                    String.format("Não existe cadastro da Produto de código %d", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Produto de código %d não pode ser removida, pois está em uso", id)
            );
        }
    }
}
