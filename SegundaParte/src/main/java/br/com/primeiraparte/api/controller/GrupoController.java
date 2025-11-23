package br.com.primeiraparte.api.controller;


import br.com.primeiraparte.domain.entity.Cozinha;
import br.com.primeiraparte.domain.entity.Grupo;
import br.com.primeiraparte.domain.exception.EntidadeEmUsoException;
import br.com.primeiraparte.domain.exception.EntidadeNaoEncontrada;
import br.com.primeiraparte.service.GrupoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/grupo")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Grupo> listar() {
        return grupoService.listar();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Grupo> buscar(@PathVariable Long id) {
        Grupo e = grupoService.buscar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/grupo/" + id);
        if(e != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(headers)
                    .body(e);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Grupo e) {
        grupoService.salvar(e);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(e.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Grupo> atualizar(@PathVariable Long id,@RequestBody Grupo g) {
        Grupo grupoAtual = grupoService.buscar(id);
        if(g == null) return ResponseEntity.notFound().build();
        BeanUtils.copyProperties(g, grupoAtual, "id");
        grupoService.salvar(grupoAtual);
        return ResponseEntity.ok(grupoAtual);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            grupoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
