package io.andresgois.algaapi.service;

import io.andresgois.algaapi.domain.Cliente;
import io.andresgois.algaapi.domain.Produto;
import io.andresgois.algaapi.notificacao.Notificador;

public class EmissaoNotaFiscalService {

	private Notificador notificador;

	public EmissaoNotaFiscalService(Notificador notificador) {
		this.notificador = notificador;
	}

	public void emitir(Cliente c, Produto p) {
		//NotificadorEmail notificadorEmail = new NotificadorEmail();
		this.notificador.notificar(c, "Nota fiscal do produto " +
				p.getNome() + " for emitida!");
	}
}
