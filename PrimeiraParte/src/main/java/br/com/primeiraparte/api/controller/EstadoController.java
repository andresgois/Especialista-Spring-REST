package br.com.primeiraparte.api.controller;

import br.com.primeiraparte.domain.EntityXml.EstadoList;
import br.com.primeiraparte.domain.entity.Estado;
import br.com.primeiraparte.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Estado> listar() {
        //List<Estado> lista = new ArrayList<>();
        //return new EstadoList(estadoRepository.listar());
        return estadoRepository.listar();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public EstadoList listarXml() {
        List<Estado> lista = new ArrayList<>();
        return new EstadoList(estadoRepository.listar());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Estado> buscar(@PathVariable Long id) {
        //return estadoRepository.buscar(id);
        Estado e = estadoRepository.buscar(id);
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
}
