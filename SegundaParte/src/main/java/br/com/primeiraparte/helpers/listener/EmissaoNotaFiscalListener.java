package br.com.primeiraparte.helpers.listener;

import br.com.primeiraparte.service.UserAtivadorEvent;
import br.com.primeiraparte.service.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmissaoNotaFiscalListener {

    @EventListener
    public void userAtivadoEvent(UserAtivadorEvent event) {
        System.out.println("Nota fiscal será emitida!");

    }
}
