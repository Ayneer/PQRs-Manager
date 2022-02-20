package com.btg.pactual.pqrs.ecxeption;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class ExceptionResponse {

    private String message;
    private Date dateTime;
    private int status;
    private Map<String, String> errors;

}
