package ru.dormlive.backend.util.exception;

public class UserNotRegisteredException extends RuntimeException{
    public UserNotRegisteredException(String message){
        super(message);
    }
}
