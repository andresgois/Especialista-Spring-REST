package io.andresgois.algaapi.notificacao;

import io.andresgois.algaapi.domain.Cliente;

//@Component
public class NotificadorSMS implements Notificador{

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando: %s\nAtravés de SMS  do telefone %s: %s\n"
				,cliente.getNome(), cliente.getTelefone(), mensagem);
	}
}
