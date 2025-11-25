package br.com.primeiraparte.service;

import br.com.primeiraparte.domain.entity.Permissao;
import br.com.primeiraparte.domain.exception.EntidadeEmUsoException;
import br.com.primeiraparte.domain.exception.EntidadeNaoEncontrada;
import br.com.primeiraparte.domain.repository.PermissaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoService {
    @Autowired
    private PermissaoRepository permissaoRepository;

    public List<Permissao> listar() {
        return permissaoRepository.findAll();
    }

    public Permissao buscar(Long id) {
        return permissaoRepository.findById(id).orElse(null);
    }

    public Permissao salvar(Permissao u) {
        return permissaoRepository.save(u);
    }

    public Permissao atualizar(Long id,Permissao u) {
        Permissao uAtual = this.buscar(id);
        if(u != null)
            BeanUtils.copyProperties(u, uAtual, "id");
        return this.salvar(uAtual);
    }

    public void deletar(Long id) {
        Permissao u = null;
        try {
            u = this.buscar(id);
            permissaoRepository.delete(u);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontrada(
                    String.format("Não existe cadastro da Permissao de código %d", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Permissao de código %d não pode ser removida, pois está em uso", id)
            );
        }
    }
}
