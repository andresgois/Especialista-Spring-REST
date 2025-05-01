package io.andresgois.algaapi.service;

import io.andresgois.algaapi.domain.Cliente;
import io.andresgois.algaapi.notificacao.Notificador;

public class AtivacaoClienteService {

	private Notificador notificador;

	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
	}

	public void ativar(Cliente cliente) {
		cliente.ativar();
		//NotificadorEmail notificadorEmail = new NotificadorEmail();
		this.notificador
				.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}
}
