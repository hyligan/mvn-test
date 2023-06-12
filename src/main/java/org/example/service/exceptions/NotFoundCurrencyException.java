package org.example.service.exceptions;

public class NotFoundCurrencyException extends RuntimeException {
    public NotFoundCurrencyException(String s) {
        super(s);
    }
}
