package br.com.primeiraparte.service;

import br.com.primeiraparte.domain.entity.Cidade;
import br.com.primeiraparte.domain.exception.EntidadeEmUsoException;
import br.com.primeiraparte.domain.exception.EntidadeNaoEncontrada;
import br.com.primeiraparte.domain.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository cidadesRepository;

    public List<Cidade> listar() {
        return cidadesRepository.listar();
    }

    public Cidade buscar(Long id) {
        return cidadesRepository.buscar(id);
    }

    public Cidade salvar(Cidade cidade) {
        return cidadesRepository.salvar(cidade);
    }

    public Cidade atualizar(Long id,Cidade cidade) {
        Cidade cidadeAtual = cidadesRepository.buscar(id);
        if(cidade != null)
            BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        return cidadesRepository.salvar(cidadeAtual);
    }

    public void deletar(Long id) {
        Cidade cidade = null;
        try {
            cidade = cidadesRepository.buscar(id);
            cidadesRepository.remover(cidade);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontrada(
                    String.format("Não existe cadastro da Cidade de código %d", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cidade de código %d não pode ser removida, pois está em uso", id)
            );
        }
    }
}
