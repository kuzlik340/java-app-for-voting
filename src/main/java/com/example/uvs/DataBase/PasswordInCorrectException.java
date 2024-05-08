package com.example.uvs.DataBase;

/**
 * Exception thrown when the provided password is incorrect.
 */
public class PasswordInCorrectException extends Exception {
    /**
     * Constructs a new PasswordInCorrectException with the specified detail message.
     *
     * @param message the detail message.
     */
    public PasswordInCorrectException(String message) {
        super(message);
    }
}
