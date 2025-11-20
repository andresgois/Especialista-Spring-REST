package br.com.primeiraparte.api.controller;

import br.com.primeiraparte.domain.entity.Restaurante;
import br.com.primeiraparte.domain.exception.EntidadeEmUsoException;
import br.com.primeiraparte.domain.exception.EntidadeNaoEncontrada;
import br.com.primeiraparte.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Map;

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

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            restauranteService.salvar(restaurante);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(restaurante.getId()).toUri();
            return ResponseEntity.created(uri).build();
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> adicionar(@PathVariable Long id,@RequestBody Restaurante restaurante) {
        try {
            Restaurante restauranteAtual = restauranteService.atualizar(id,  restaurante);
            return ResponseEntity.ok(restauranteAtual);
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
        try {
            restauranteService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> atualizarPacial(@PathVariable Long id,@RequestBody Map<String, Object> campos) {
        try {
            Restaurante restauranteAtual = restauranteService.atualizarPacial(id,   campos);
            return ResponseEntity.ok(restauranteAtual);
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/por-nome")
    public ResponseEntity<List<Restaurante>> consultarPorNome(@RequestParam("nome") String nome, @RequestParam("id") String id) {
        return ResponseEntity.ok(restauranteService.consultarPorNome(nome, id));
    }

    @GetMapping("/restaurantes/por-nome-e-frete")
    public List<Restaurante> restaurantesPorNomeFrete(String nome,
                                                      BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        return restauranteService.find(nome, taxaFreteInicial, taxaFreteFinal);
    }

    @GetMapping("/restaurantes/com-frete-gratis")
    public List<Restaurante> restaurantesComFreteGratis(String nome) {
        //var comFreteGratis = new RestauranteComFreteGratisSpec();
        //var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);

        //return restauranteService.findAll(comFreteGratis.and(comNomeSemelhante));
        return restauranteService.findAll(nome);
    }
}
