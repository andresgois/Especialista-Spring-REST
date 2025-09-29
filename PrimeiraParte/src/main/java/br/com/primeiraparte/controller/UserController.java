package br.com.primeiraparte.controller;

import br.com.primeiraparte.domain.entity.User;
import br.com.primeiraparte.service.AtivacaoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AtivacaoUserService ativacaoUserService;

    @PostMapping("/{id}")
    public ResponseEntity salvar(@PathVariable String id) {
        ativacaoUserService.ativarUsuario(id);
        return ResponseEntity.ok("Usuário Ativado com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<User>> listar() {
        List<User> users = ativacaoUserService.listar();
        return ResponseEntity.ok(users);
    }
}
