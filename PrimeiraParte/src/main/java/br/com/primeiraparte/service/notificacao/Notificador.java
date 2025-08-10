package br.com.primeiraparte.service.notificacao;

import br.com.primeiraparte.domain.entity.User;

public interface Notificador {

        public void notificar(User user, String mensagem);

}
