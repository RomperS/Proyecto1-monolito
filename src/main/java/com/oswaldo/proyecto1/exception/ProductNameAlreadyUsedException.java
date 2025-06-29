package com.oswaldo.proyecto1.exception;

public class ProductNameAlreadyUsedException extends RuntimeException {
    public ProductNameAlreadyUsedException(String message) {
        super(message);
    }
}
