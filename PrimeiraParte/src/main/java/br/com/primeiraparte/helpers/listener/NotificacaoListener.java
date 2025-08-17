package br.com.primeiraparte.helpers.listener;

import br.com.primeiraparte.service.UserAtivadorEvent;
import br.com.primeiraparte.service.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoListener {

    @Autowired
    private Notificador notificador;

    @EventListener
    public void userAtivadoEvent(UserAtivadorEvent event) {
        System.out.println(event.getUser().getName()+ " - Seu cadastro no sistema está ativo!");
        notificador.notificar(event.getUser(), "Email enviado!");
    }
}
