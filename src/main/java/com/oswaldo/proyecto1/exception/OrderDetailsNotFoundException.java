package com.oswaldo.proyecto1.exception;

public class OrderDetailsNotFoundException extends RuntimeException {
    public OrderDetailsNotFoundException(String message) {
        super(message);
    }
}
