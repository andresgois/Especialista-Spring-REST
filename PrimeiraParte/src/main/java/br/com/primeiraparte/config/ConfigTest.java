package br.com.primeiraparte.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ConfigTest {


    @Bean
    @Profile("dev")
    public String dev() {
        System.out.println("------------------- dev");
        return "dev";
    }

    @Bean
    @Profile("prod")
    public String prod() {
        System.out.println("------------------- PROD");
        return "prod";
    }

    //@Bean(initMethod = "init", destroyMethod = "destroy") // deve está na classe onde cria a instancia / injeta a dependência
    public void prePostConstruct() {
        System.out.println("------------------ prePostConstruct");
    }

    @PostConstruct
    public void init() {
        System.out.println("------------------- init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("------------------ DESTROY");
    }

}

