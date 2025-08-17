package br.com.primeiraparte.service;

import br.com.primeiraparte.domain.entity.User;
import br.com.primeiraparte.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AtivacaoUserService {

    //private Notificador notificador;

    //public AtivacaoClienteService(Notificador notificador) {
        //this.notificador = notificador;
    //}

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void ativarUsuario(User user) {
        user.ativar();
        //NotificadorEmail notificadorEmail = new NotificadorEmail();
        //this.notificador.notificar(user, "Seu cadastro no sistema está ativo!");
        eventPublisher.publishEvent(new UserAtivadorEvent(user));
    }

    public void ativarUsuario(String id) {
        User user = (User) userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        ativarUsuario(user);
        userRepository.save(user);
    }

    public List<User> listar() {
        return userRepository.findAll();
    }
}
