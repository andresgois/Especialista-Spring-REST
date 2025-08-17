package br.com.primeiraparte.testesJpa;

import br.com.primeiraparte.PrimeiraParteApplication;
import br.com.primeiraparte.domain.entity.User;
import br.com.primeiraparte.domain.enuns.Ativado;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new SpringApplicationBuilder(PrimeiraParteApplication.class)
                .web(WebApplicationType.NONE).run(args);

        // Lista todos
        Repository c = context.getBean(Repository.class);
        List<User> users = c.findByAllUser();

        users.forEach(user -> System.out.println(user.getName()));

        // Cria usuário
        User u = new User();
        u.setName("bia");
        u.setAge(15);
        u.setPassword("1456988");
        u.setLogin("BiaGois");
        u.setEmail("bia@email.com");
        u.setAtivado(Ativado.NAO);

        User UserCriado = c.createUser(u);
        System.out.println(UserCriado.getName());
    }
}
