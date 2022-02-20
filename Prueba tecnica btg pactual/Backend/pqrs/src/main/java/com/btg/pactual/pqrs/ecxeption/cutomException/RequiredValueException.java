package com.btg.pactual.pqrs.ecxeption.cutomException;

public class RequiredValueException extends Exception {
    public RequiredValueException(String message){
        super(message);
    }
}
