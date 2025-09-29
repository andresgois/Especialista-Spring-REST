package br.com.primeiraparte.service;

import br.com.primeiraparte.domain.entity.User;

public class UserAtivadorEvent {
    
    private User user;

    public UserAtivadorEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
