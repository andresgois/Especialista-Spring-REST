package br.com.primeiraparte.api.controller;


import br.com.primeiraparte.domain.entity.Cozinha;
import br.com.primeiraparte.domain.entity.Usuario;
import br.com.primeiraparte.domain.exception.EntidadeEmUsoException;
import br.com.primeiraparte.domain.exception.EntidadeNaoEncontrada;
import br.com.primeiraparte.service.UsuarioService;
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
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        Usuario e = usuarioService.buscar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/usuario/" + id);
        if(e != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(headers)
                    .body(e);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> adicionar(@RequestBody Usuario e) {
        usuarioService.salvar(e);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(e.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id,@RequestBody Usuario g) {
        Usuario atual = usuarioService.buscar(id);
        if(g == null) return ResponseEntity.notFound().build();
        BeanUtils.copyProperties(g, atual, "id");
        usuarioService.salvar(atual);
        return ResponseEntity.ok(atual);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            usuarioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
