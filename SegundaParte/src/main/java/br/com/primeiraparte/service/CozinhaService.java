package br.com.primeiraparte.service;

import br.com.primeiraparte.domain.entity.Cozinha;
import br.com.primeiraparte.domain.exception.EntidadeEmUsoException;
import br.com.primeiraparte.domain.exception.EntidadeNaoEncontrada;
import br.com.primeiraparte.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaService {

    private static final String MSG_COZINHA_EM_USO
            = "Cozinha de código %d não pode ser removida, pois está em uso";

    private static final String MSG_COZINHA_NAO_ENCONTRADA
            = "Não existe um cadastro de cozinha com código %d";

    @Autowired
    private CozinhaRepository cozinhasRepository;

    public List<Cozinha> listar() {
        return cozinhasRepository.findAll();
    }

    public List<Cozinha> buscarPorNome(String nome) {
        return cozinhasRepository.findCozinhaByNomeContaining(nome);
    }

    public Cozinha buscar(Long id) {
        return cozinhasRepository.findById(id).orElse(null);
    }

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhasRepository.save(cozinha);
    }

    public Cozinha adicionar(Long id,Cozinha cozinha) {
        Cozinha cozinhaAtual = this.buscar(id);
        if(cozinha == null)
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return this.salvar(cozinhaAtual);
    }

    /*public void deletar(Long id) {
        Cozinha cozinha = null;
        try {
            cozinha = this.buscar(id);
            ozinhasRepository.delete(cozinha);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontrada(
                    String.format("Não existe cadastro da Cozinha de código %d", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", id)
            );
        }
    }*/

    public void deletar(Long id) {
        try {
            cozinhasRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontrada(
                    String.format(MSG_COZINHA_NAO_ENCONTRADA, id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_COZINHA_EM_USO, id)
            );
        }
    }

    public Cozinha buscarOuFalhar(Long cozinhaId) {
        return cozinhasRepository.findById(cozinhaId)
                .orElseThrow(() -> new EntidadeNaoEncontrada(
                        String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId)));
    }
}

