package br.com.primeiraparte.helpers.listener;

import br.com.primeiraparte.service.UserAtivadorEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoListener {

    @EventListener
    public void userAtivadoEvent(UserAtivadorEvent event) {
        System.out.println(event.getUser().getName()+ "Seu cadastro no sistema está ativo!");
    }
}
