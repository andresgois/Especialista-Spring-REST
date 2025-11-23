package br.com.primeiraparte.api.controller;


import br.com.primeiraparte.domain.entity.Produto;
import br.com.primeiraparte.domain.exception.EntidadeEmUsoException;
import br.com.primeiraparte.domain.exception.EntidadeNaoEncontrada;
import br.com.primeiraparte.service.ProdutoService;
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
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Produto> listar() {
        return produtoService.listar();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        Produto e = produtoService.buscar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/produto/" + id);
        if(e != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(headers)
                    .body(e);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Produto> adicionar(@RequestBody Produto p) {
        produtoService.salvar(p);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id,@RequestBody Produto produto) {
        Produto obj = produtoService.buscar(id);
        if(produto == null) return ResponseEntity.notFound().build();
        BeanUtils.copyProperties(produto, obj, "id");
        produtoService.salvar(obj);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            produtoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
