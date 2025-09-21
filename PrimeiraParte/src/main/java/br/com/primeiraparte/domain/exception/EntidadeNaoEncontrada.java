package br.com.primeiraparte.domain.exception;

public class EntidadeNaoEncontrada extends RuntimeException {
    public EntidadeNaoEncontrada(String message) {
        super(message);
    }
}
