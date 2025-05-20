package io.andresgois.algaapi.config;

import io.andresgois.algaapi.notificacao.NotificadorEmail;
import io.andresgois.algaapi.service.AtivacaoClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificadorConfig {

	@Bean
	public NotificadorEmail NotificadorEmail(){
		NotificadorEmail notificadorEmail = new NotificadorEmail("http://host.com.br");
		notificadorEmail.setCaixaAlta(true);
		return notificadorEmail;
	}

	@Bean
	public AtivacaoClienteService ativacaoClienteService(){
		return new AtivacaoClienteService(NotificadorEmail());
	}
}
