package br.com.primeiraparte.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificador.email")
public class NotificadorPropertiesConfig {

    private String servidorHost;
    private Integer servidorPort = 60;

    public String getServidorHost() {
        return servidorHost;
    }

    public void setServidorHost(String servidorHost) {
        this.servidorHost = servidorHost;
    }

    public Integer getServidorPort() {
        return servidorPort;
    }

    public void setServidorPort(Integer servidorPort) {
        this.servidorPort = servidorPort;
    }
}

