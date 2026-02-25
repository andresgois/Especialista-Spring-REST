package br.com.primeiraparte.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.lang.Nullable;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontrada extends ResponseStatusException {

    public EntidadeNaoEncontrada(HttpStatusCode status, @Nullable String mensagem) {
        super(status, mensagem);
    }

    public EntidadeNaoEncontrada(String message) {
        this(HttpStatus.NOT_FOUND, message);
    }
}
