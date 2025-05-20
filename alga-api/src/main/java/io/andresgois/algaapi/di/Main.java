package io.andresgois.algaapi.di;

import io.andresgois.algaapi.domain.Cliente;
import io.andresgois.algaapi.notificacao.NotificadorSMS;
import io.andresgois.algaapi.service.AtivacaoClienteService;

public class Main {
	public static void main(String[] args) {
		Cliente c = new Cliente("Andre","andre@email.com","85 99412536");
		c.ativar();
		AtivacaoClienteService ativar = new AtivacaoClienteService(new NotificadorSMS());
		ativar.ativar(c);
	}
}
