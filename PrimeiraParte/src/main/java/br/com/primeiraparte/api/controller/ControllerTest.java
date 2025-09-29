package br.com.primeiraparte.api.controller;

import br.com.primeiraparte.domain.entity.Cozinha;
import br.com.primeiraparte.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teste")
public class ControllerTest {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping("por-nome")
    public List<Cozinha> buscarPorNome(@RequestParam("nome") String nome)
    {
        return cozinhaRepository.buscar(nome);
    }
}
