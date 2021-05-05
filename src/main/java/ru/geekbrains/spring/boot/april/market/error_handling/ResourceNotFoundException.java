package ru.geekbrains.spring.boot.april.market.error_handling;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
