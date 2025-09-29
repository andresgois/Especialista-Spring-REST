package br.com.primeiraparte.api.controller;

import br.com.primeiraparte.domain.entity.Cidade;
import br.com.primeiraparte.domain.exception.EntidadeEmUsoException;
import br.com.primeiraparte.domain.exception.EntidadeNaoEncontrada;
import br.com.primeiraparte.service.CidadeService;
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
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cidade> listar() {
        return cidadeService.listar();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long id) {
        Cidade e = cidadeService.buscar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cidades/" + id);
        if(e != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(headers)
                    .body(e);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cidade> adicionar(@RequestBody Cidade cidade) {
        cidadeService.salvar(cidade);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cidade.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long id,@RequestBody Cidade cidade) {
        Cidade cidadeAtual = cidadeService.buscar(id);
        if(cidade == null) return ResponseEntity.notFound().build();
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        cidadeService.salvar(cidadeAtual);
        return ResponseEntity.ok(cidadeAtual);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            cidadeService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
