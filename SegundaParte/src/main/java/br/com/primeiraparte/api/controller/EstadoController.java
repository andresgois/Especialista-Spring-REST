package br.com.primeiraparte.api.controller;


import br.com.primeiraparte.domain.entity.Cozinha;
import br.com.primeiraparte.domain.entity.Estado;
import br.com.primeiraparte.domain.exception.EntidadeEmUsoException;
import br.com.primeiraparte.domain.exception.EntidadeNaoEncontrada;
import br.com.primeiraparte.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Estado> listar() {
        return estadoService.listar();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Estado> buscar(@PathVariable Long id) {
        //return estadoService.buscar(id);
        Estado e = estadoService.buscar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/estados/" + id);
        //return ResponseEntity.ok(e);
        //return ResponseEntity.status(HttpStatus.OK).body(e);
        if(e != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(headers)
                    .body(e);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Estado estado) {
        estadoService.salvar(estado);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(estado.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long id,@RequestBody Estado estado) {
        Estado estadoAtual = estadoService.buscar(id);
        if(estado == null) return ResponseEntity.notFound().build();
        BeanUtils.copyProperties(estado, estadoAtual, "id");
        estadoService.salvar(estadoAtual);
        return ResponseEntity.ok(estadoAtual);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            estadoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
