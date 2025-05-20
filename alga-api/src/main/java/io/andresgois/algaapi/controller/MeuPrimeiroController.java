package io.andresgois.algaapi.controller;

import io.andresgois.algaapi.domain.Cliente;
import io.andresgois.algaapi.notificacao.Notificador;
import io.andresgois.algaapi.notificacao.NotificadorEmail;
import io.andresgois.algaapi.service.AtivacaoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeuPrimeiroController {

	//@Autowired
	private Notificador notificador;

	public MeuPrimeiroController(Notificador notificador) {
		this.notificador = notificador;
	}

	@GetMapping("/home")
	public String home() {
		Cliente c = new Cliente("Andre", "andre@email.com", "85 99412536");
		c.ativar();
		AtivacaoClienteService ativar = new AtivacaoClienteService(new NotificadorEmail());
		ativar.ativar(c);

		return "Hello World 2!";

	}
}
