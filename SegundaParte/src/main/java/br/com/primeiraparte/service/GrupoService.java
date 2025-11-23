package br.com.primeiraparte.service;

import br.com.primeiraparte.domain.entity.Grupo;
import br.com.primeiraparte.domain.exception.EntidadeEmUsoException;
import br.com.primeiraparte.domain.exception.EntidadeNaoEncontrada;
import br.com.primeiraparte.domain.repository.GrupoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {
    @Autowired
    private GrupoRepository grupoRepository;

    public List<Grupo> listar() {
        return grupoRepository.findAll();
    }

    public Grupo buscar(Long id) {
        return grupoRepository.findById(id).orElse(null);
    }

    public Grupo salvar(Grupo cidade) {
        return grupoRepository.save(cidade);
    }

    public Grupo atualizar(Long id,Grupo cidade) {
        Grupo cidadeAtual = this.buscar(id);
        if(cidade != null)
            BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        return this.salvar(cidadeAtual);
    }

    public void deletar(Long id) {
        Grupo cidade = null;
        try {
            cidade = this.buscar(id);
            grupoRepository.delete(cidade);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontrada(
                    String.format("Não existe cadastro da Grupo de código %d", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Grupo de código %d não pode ser removida, pois está em uso", id)
            );
        }
    }
}
