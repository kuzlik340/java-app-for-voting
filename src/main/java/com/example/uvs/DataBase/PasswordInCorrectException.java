package com.example.uvs.DataBase;

public class PasswordInCorrectException extends Exception{
    public PasswordInCorrectException(String message) {
        super(message);
    }
}
