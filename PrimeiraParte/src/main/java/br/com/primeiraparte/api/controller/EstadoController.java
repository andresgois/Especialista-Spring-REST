package br.com.primeiraparte.api.controller;

import br.com.primeiraparte.domain.EntityXml.EstadoList;
import br.com.primeiraparte.domain.entity.Estado;
import br.com.primeiraparte.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public EstadoList listarXml() {
        List<Estado> lista = new ArrayList<>();
        return new EstadoList(estadoRepository.listar());
    }

    @GetMapping(value = "/{id}")
    public Estado buscar(@PathVariable Long id) {
        return estadoRepository.buscar(id);
    }
}
