package br.com.primeiraparte.service;

import static br.com.primeiraparte.infrastructure.repository.spec.RestauranteSpecs.*;
import br.com.primeiraparte.domain.entity.Cozinha;
import br.com.primeiraparte.domain.entity.Restaurante;
import br.com.primeiraparte.domain.exception.EntidadeEmUsoException;
import br.com.primeiraparte.domain.exception.EntidadeNaoEncontrada;
import br.com.primeiraparte.domain.repository.CozinhaRepository;
import br.com.primeiraparte.domain.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static br.com.primeiraparte.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static br.com.primeiraparte.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

@Service
public class RestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscar(Long id) {
        return restauranteRepository.findById(id).orElse(null);
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long id = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(id).orElse(null);
        if(cozinha == null) {
            throw new EntidadeNaoEncontrada(
                    String.format("Não existe cadastro da Restaurante de código %d", id)
            );
        }
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    public Restaurante adicionar(Long id,Restaurante restaurante) {
        Restaurante restauranteAtual = this.buscar(id);
        if(restaurante == null)
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
        return this.salvar(restauranteAtual);
    }

    public Restaurante atualizar(Long id,Restaurante restaurante) {
        Restaurante restauranteAtual = this.buscar(id);
        if(restaurante == null)
            throw new EntidadeNaoEncontrada(
                    String.format("Não existe cadastro da Restaurante de código %d", id)
            );
        Cozinha cozinha = cozinhaRepository.findById(restauranteAtual.getCozinha().getId()).orElse(null);
        if(cozinha == null) {
            throw new EntidadeNaoEncontrada(
                    String.format("Não existe cadastro da Cozinha de código %d", id)
            );
        }
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id","formaPagamentos");
        return this.salvar(restauranteAtual);
    }

    public void deletar(Long id) {
        Restaurante restaurante = null;
        try {
            restaurante = this.buscar(id);
            restauranteRepository.delete(restaurante);
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

    public Restaurante atualizarPacial(Long id, Map<String, Object> campos) {
        Restaurante restauranteAtual = restauranteRepository.findById(id).orElse(null);

        if(restauranteAtual == null)
            throw new EntidadeNaoEncontrada(
                    String.format("Não existe cadastro da Restaurante de código %d", id)
            );
        merge(campos, restauranteAtual);
        Cozinha cozinha = cozinhaRepository.findById(restauranteAtual.getCozinha().getId()).orElse(null);
        return this.atualizar(id, restauranteAtual);
    }

    private static void merge(Map<String, Object> dadosOrigem, Restaurante resrauranteDestino) {
        //converte json em java
        ObjectMapper mapper = new ObjectMapper();
        // cria uma instancia do tipo restaurante com base nos dados de origem
        Restaurante restauranteOrigem = mapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((key, value) -> {
            // pega a chave e procura a sua equivalência na entidade Restaurante
            Field field = ReflectionUtils.findField(Restaurante.class, key);
            // Na entidade as propriedades são privadas, precisamos desativar isso para poder preencher aqui
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
            System.out.println(key+ " : " + value);
            ReflectionUtils.setField(field, resrauranteDestino, novoValor);
        });
    }

    public List<Restaurante> consultarPorNome(String nome,String conzinnhaId) {
        return restauranteRepository.consultarPorNome(nome, conzinnhaId);
    }

    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
    }

    public List<Restaurante> findAll(String nome) {
        /*var comFreteGratis = new RestauranteComFreteGratisSpec();
        var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);

        return restauranteRepository.findAll(comFreteGratis.and(comNomeSemelhante));*/
        /*return restauranteRepository.findAll(comFreteGratis()
                .and(comNomeSemelhante(nome)));*/

        return restauranteRepository.findComFreteGratis(nome);
    }
}
