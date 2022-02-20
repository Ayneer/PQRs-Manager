package com.btg.pactual.pqrs.ecxeption.cutomException;

public class PqrNotFoundException extends RuntimeException{
    public PqrNotFoundException(String message){
        super(message);
    }
}
