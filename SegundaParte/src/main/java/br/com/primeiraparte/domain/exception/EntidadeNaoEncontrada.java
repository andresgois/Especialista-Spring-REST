package br.com.primeiraparte.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
/*
public class EntidadeNaoEncontrada extends ResponseStatusException {

    public EntidadeNaoEncontrada(HttpStatusCode status, @Nullable String mensagem) {
        super(status, mensagem);
    }

    public EntidadeNaoEncontrada(String message) {
        this(HttpStatus.NOT_FOUND, message);
    }
}
*/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontrada extends RuntimeException {

    public EntidadeNaoEncontrada(String message) {
        super(message);
    }
}

