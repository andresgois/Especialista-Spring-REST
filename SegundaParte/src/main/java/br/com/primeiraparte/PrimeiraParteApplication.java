package br.com.primeiraparte;

import br.com.primeiraparte.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class PrimeiraParteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimeiraParteApplication.class, args);
	}

}
