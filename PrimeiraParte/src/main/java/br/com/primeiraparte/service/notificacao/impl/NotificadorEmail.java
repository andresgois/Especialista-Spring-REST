package br.com.primeiraparte.service.notificacao.impl;

import br.com.primeiraparte.domain.entity.User;
import br.com.primeiraparte.service.notificacao.Notificador;

public class NotificadorEmail implements Notificador {

    private boolean caixaAlta;
    private String host;

    public NotificadorEmail(String host) {
        this.host = host;
    }

    @Override
    public void notificar(User user, String mensagem) {
        if(isCaixaAlta()){
            mensagem = mensagem.toUpperCase();
        }
        System.out.printf("Notificando: %s\nAtravés do e-mail %s: %s\n"
                ,user.getName(), user.getEmail(), mensagem);
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
