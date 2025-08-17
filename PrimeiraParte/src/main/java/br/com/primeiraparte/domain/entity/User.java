package br.com.primeiraparte.domain.entity;

import br.com.primeiraparte.domain.enuns.Ativado;
import br.com.primeiraparte.helpers.converters.AtivadoConverter;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @Column(columnDefinition = "CHAR(36)")
    //private UUID id;
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String email;

    @Convert(converter = AtivadoConverter.class)
    @Column(length = 1, nullable = false)
    private Ativado ativado;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String login;

    // Construtores
    public User() {}

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    public User(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.login = login;
    }

    public User(String name, int age, String password, String login,String email) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.login = login;
        this.ativado = Ativado.NAO;
        this.email = email;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void ativar() {
        this.ativado = Ativado.SIM;
    }

    public void desativar() {
        this.ativado = Ativado.NAO;
    }

    public Ativado getAtivado() {
        return ativado;
    }

    public void setAtivado(Ativado ativado) {
        this.ativado = ativado;
    }
}

