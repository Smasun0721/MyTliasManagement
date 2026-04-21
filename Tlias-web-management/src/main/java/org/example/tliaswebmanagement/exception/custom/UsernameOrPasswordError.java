package org.example.tliaswebmanagement.exception.custom;

public class UsernameOrPasswordError extends RuntimeException {
    public UsernameOrPasswordError(String message) {
        super(message);
    }
}
