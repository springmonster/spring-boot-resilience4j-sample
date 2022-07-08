package com.example.resilience4j.exception;

public class ChaosException extends RuntimeException {

    public ChaosException(String message) {
        super(message);
    }
}
