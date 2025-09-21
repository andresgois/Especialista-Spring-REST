package br.com.primeiraparte.service;

import br.com.primeiraparte.domain.entity.Cozinha;
import br.com.primeiraparte.domain.entity.Restaurante;
import br.com.primeiraparte.domain.exception.EntidadeEmUsoException;
import br.com.primeiraparte.domain.exception.EntidadeNaoEncontrada;
import br.com.primeiraparte.domain.repository.CozinhaRepository;
import br.com.primeiraparte.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    public Restaurante buscar(Long id) {
        return restauranteRepository.buscar(id);
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long id = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(id);
        if(cozinha == null) {
            throw new EntidadeNaoEncontrada(
                    String.format("Não existe cadastro da Restaurante de código %d", id)
            );
        }
        restaurante.setCozinha(cozinha);
        return restauranteRepository.salvar(restaurante);
    }

    public Restaurante adicionar(Long id,Restaurante restaurante) {
        Restaurante restauranteAtual = restauranteRepository.buscar(id);
        if(restaurante == null)
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
        return restauranteRepository.salvar(restauranteAtual);
    }

    public Restaurante atualizar(Long id,Restaurante restaurante) {
        Restaurante restauranteAtual = restauranteRepository.buscar(id);
        if(restaurante == null)
            throw new EntidadeNaoEncontrada(
                    String.format("Não existe cadastro da Restaurante de código %d", id)
            );
        Cozinha cozinha = cozinhaRepository.buscar(restauranteAtual.getCozinha().getId());
        if(cozinha == null) {
            throw new EntidadeNaoEncontrada(
                    String.format("Não existe cadastro da Cozinha de código %d", id)
            );
        }
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
        return restauranteRepository.salvar(restauranteAtual);
    }

    public void deletar(Long id) {
        Restaurante restaurante = null;
        try {
            restaurante = restauranteRepository.buscar(id);
            restauranteRepository.remover(restaurante);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontrada(
                    String.format("Não existe cadastro da Restaurante de código %d", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Restaurante de código %d não pode ser removida, pois está em uso", id)
            );
        }
    }
}
