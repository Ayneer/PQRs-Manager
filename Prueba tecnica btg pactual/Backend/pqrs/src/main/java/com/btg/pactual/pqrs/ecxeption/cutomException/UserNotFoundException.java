package com.btg.pactual.pqrs.ecxeption.cutomException;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
