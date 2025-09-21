package br.com.primeiraparte.api.controller;

import br.com.primeiraparte.domain.entity.Restaurante;
import br.com.primeiraparte.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteService.listar();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        Restaurante restaurante = restauranteService.buscar(id);
        if(restaurante == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(restaurante);
    }

    /*@PostMapping
    public ResponseEntity<Restaurante> adicionar(@RequestBody Restaurante restaurante) {
        restauranteService.salvar(restaurante);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(restaurante.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Restaurante> adicionar(@PathVariable Long id,@RequestBody Restaurante restaurante) {
        Restaurante restauranteAtual = restauranteService.buscar(id);
        if(restaurante == null) return ResponseEntity.notFound().build();
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
        restauranteService.salvar(restauranteAtual);
        return ResponseEntity.ok(restauranteAtual);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        /*try {
            Restaurante restaurante = restauranteService.buscar(id);
            if(restaurante == null)
                return ResponseEntity.notFound().build();
            restauranteService.deletar(restaurante.getId());
        } catch (DataIntegrityViolationException e) {
            //return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.noContent().build();*/
        /*try {
            restauranteService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }*/
}
