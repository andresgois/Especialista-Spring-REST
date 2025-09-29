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
        return estadosRepository.findAll();
    }

    public Estado buscar(Long id) {
        return estadosRepository.findById(id).orElse(null);
    }

    public Estado salvar(Estado estado) {
        return estadosRepository.save(estado);
    }

    public Estado atualizar(Long id,Estado estado) {
        Estado estadoAtual = this.buscar(id);
        if(estado != null)
            BeanUtils.copyProperties(estado, estadoAtual, "id");
        return this.salvar(estadoAtual);
    }

    public void deletar(Long id) {
        Estado estado = null;
        try {
            estado = this.buscar(id);
            estadosRepository.delete(estado);
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
