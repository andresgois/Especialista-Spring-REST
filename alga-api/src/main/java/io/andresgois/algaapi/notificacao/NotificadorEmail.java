package io.andresgois.algaapi.notificacao;

import io.andresgois.algaapi.domain.Cliente;

public class NotificadorEmail implements Notificador{

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando: %s\nAtravés do e-mail %s: %s\n"
				,cliente.getNome(), cliente.getEmail(), mensagem);
	}
}
