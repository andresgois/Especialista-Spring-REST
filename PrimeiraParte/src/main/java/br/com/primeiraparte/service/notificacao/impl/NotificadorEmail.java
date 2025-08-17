package br.com.primeiraparte.service.notificacao.impl;

import br.com.primeiraparte.config.NotificadorPropertiesConfig;
import br.com.primeiraparte.domain.entity.User;
import br.com.primeiraparte.domain.enuns.NivelUrganecia;
import br.com.primeiraparte.helpers.annotations.TipoNoficador;
import br.com.primeiraparte.service.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@TipoNoficador(NivelUrganecia.URGENTE)
@Component
public class NotificadorEmail implements Notificador {

    private boolean caixaAlta;

    /*@Value("${notificador.email.servidor-host}")
    private String host;
    // usando configurações de properties agora
    @Value("${notificador.email.servidor-port}")
    private Integer port;*/

    @Autowired
    private NotificadorPropertiesConfig properties;

    /*public NotificadorEmail(String host) {
        this.host = host;
    }*/

    @Override
    public void notificar(User user, String mensagem) {
        if(isCaixaAlta()){
            mensagem = mensagem.toUpperCase();
        }
        System.out.printf("Notificando: %s\nAtravés do e-mail %s: %s\n"
                ,user.getName(), user.getEmail(), mensagem);
        //System.out.printf("Host:"+host+"\nPort:"+port+"\n");
        System.out.printf("Host:"+properties.getServidorHost()+"\nPort:"+properties.getServidorPort()+"\n");
    }

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }


    public boolean isCaixaAlta() {
        return caixaAlta;
    }
    public NotificadorEmail() {
        System.out.println("NotificadorEmail");
    }

    /*public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }*/
}
