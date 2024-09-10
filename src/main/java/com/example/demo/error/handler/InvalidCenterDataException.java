package com.example.demo.error.handler;

public class InvalidCenterDataException extends RuntimeException {
    public InvalidCenterDataException(String message) {
        super(message);
    }
}