package br.com.primeiraparte.testesJpa;

import br.com.primeiraparte.PrimeiraParteApplication;
import br.com.primeiraparte.domain.entity.User;
import br.com.primeiraparte.domain.enuns.Ativado;
import br.com.primeiraparte.domain.repository.UserRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new SpringApplicationBuilder(PrimeiraParteApplication.class)
                .web(WebApplicationType.NONE).run(args);

        // Lista todos
        // Sem padrão
        // Repository c = context.getBean(Repository.class);

        // Padrão repository
        UserRepository c = context.getBean(UserRepository.class);
        List<User> users = c.findByAllUser();

        users.forEach(user -> System.out.println(user.getName()));

        // Cria usuário
        /*User u = new User();
        u.setName("bia");
        u.setAge(15);
        u.setPassword("1456988");
        u.setLogin("BiaGois");
        u.setEmail("bia@email.com");
        u.setAtivado(Ativado.NAO);

        User UserCriado = c.salvar(u);
        System.out.println(UserCriado.getName());*/

        User user1 = c.findByUser("15ebbe31-7b21-11f0-a116-fed138f6b576");
        System.out.println(user1.getName());

        // deleta o usuário
        c.deleteUser("15eb67e9-7b21-11f0-a116-fed138f6b576");
    }
}
