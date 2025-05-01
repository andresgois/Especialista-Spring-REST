package io.andresgois.algaapi.notificacao;

import io.andresgois.algaapi.domain.Cliente;

public interface Notificador {

	public void notificar(Cliente cliente, String mensagem);

}
