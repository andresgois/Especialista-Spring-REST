package br.com.primeiraparte.service;

import br.com.primeiraparte.domain.entity.Estado;
import br.com.primeiraparte.domain.exception.EntidadeEmUsoException;
import br.com.primeiraparte.domain.exception.EntidadeNaoEncontrada;
import br.com.primeiraparte.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository estadosRepository;

    public List<Estado> listar() {
        return estadosRepository.listar();
    }

    public Estado buscar(Long id) {
        return estadosRepository.buscar(id);
    }

    public Estado salvar(Estado estado) {
        return estadosRepository.salvar(estado);
    }

    public Estado atualizar(Long id,Estado estado) {
        Estado estadoAtual = estadosRepository.buscar(id);
        if(estado != null)
            BeanUtils.copyProperties(estado, estadoAtual, "id");
        return estadosRepository.salvar(estadoAtual);
    }

    public void deletar(Long id) {
        Estado estado = null;
        try {
            estado = estadosRepository.buscar(id);
            estadosRepository.remover(estado);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontrada(
                    String.format("Não existe cadastro da Estado de código %d", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Estado de código %d não pode ser removida, pois está em uso", id)
            );
        }
    }
}
