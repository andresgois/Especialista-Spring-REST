package br.com.primeiraparte.api.controller;

import br.com.primeiraparte.domain.entity.Cozinha;
import br.com.primeiraparte.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhasRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhasRepository.listar();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        Cozinha cozinha = cozinhasRepository.buscar(id);
        return ResponseEntity.ok(cozinha);
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        cozinhasRepository.salvar(cozinha);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cozinha.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cozinha> adicionar(@PathVariable Long id,@RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhasRepository.buscar(id);
        if(cozinha == null) return ResponseEntity.notFound().build();
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        cozinhasRepository.salvar(cozinhaAtual);
        return ResponseEntity.ok(cozinhaAtual);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            Cozinha cozinha = cozinhasRepository.buscar(id);
            if(cozinha == null) return ResponseEntity.notFound().build();
            cozinhasRepository.remover(cozinha);
        } catch (DataIntegrityViolationException e) {
            //return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.noContent().build();
    }
}
