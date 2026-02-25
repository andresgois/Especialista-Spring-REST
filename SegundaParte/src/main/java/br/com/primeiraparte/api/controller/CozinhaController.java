package br.com.primeiraparte.api.controller;

import br.com.primeiraparte.domain.entity.Cozinha;
import br.com.primeiraparte.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaService.listar();
    }

    @GetMapping("por-nome")
    public List<Cozinha> buscarPorNome(@RequestParam("nome") String nome) {
        return cozinhaService.buscarPorNome(nome);
    }

    @GetMapping(value = "/{id}")
    public Cozinha buscar(@PathVariable Long id) {
        return cozinhaService.buscarOuFalhar(id);
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        cozinhaService.salvar(cozinha);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cozinha.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public Cozinha adicionar(@PathVariable Long id,@RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(id);
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return cozinhaService.salvar(cozinhaAtual);
    }

    /*@DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            cozinhaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }*/
    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable Long id) {
        cozinhaService.deletar(id);
    }
}
