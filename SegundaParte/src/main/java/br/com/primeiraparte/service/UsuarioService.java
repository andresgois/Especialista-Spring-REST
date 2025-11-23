package br.com.primeiraparte.service;

import br.com.primeiraparte.domain.entity.Usuario;
import br.com.primeiraparte.domain.exception.EntidadeEmUsoException;
import br.com.primeiraparte.domain.exception.EntidadeNaoEncontrada;
import br.com.primeiraparte.domain.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario salvar(Usuario u) {
        return usuarioRepository.save(u);
    }

    public Usuario atualizar(Long id,Usuario u) {
        Usuario uAtual = this.buscar(id);
        if(u != null)
            BeanUtils.copyProperties(u, uAtual, "id");
        return this.salvar(uAtual);
    }

    public void deletar(Long id) {
        Usuario u = null;
        try {
            u = this.buscar(id);
            usuarioRepository.delete(u);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontrada(
                    String.format("Não existe cadastro da Usuario de código %d", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Usuario de código %d não pode ser removida, pois está em uso", id)
            );
        }
    }
}
