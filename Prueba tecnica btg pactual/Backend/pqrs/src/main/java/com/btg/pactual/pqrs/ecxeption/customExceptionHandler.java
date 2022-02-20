package com.btg.pactual.pqrs.ecxeption;

import com.btg.pactual.pqrs.ecxeption.cutomException.PqrNotFoundException;
import com.btg.pactual.pqrs.ecxeption.cutomException.RequiredValueException;
import com.btg.pactual.pqrs.ecxeption.cutomException.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class customExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleExceptions(UserNotFoundException exception, WebRequest webRequest){
        return response(exception, webRequest, HttpStatus.NOT_FOUND, exception.getMessage(), null);
    }

    @ExceptionHandler({PqrNotFoundException.class})
    public ResponseEntity<Object> handleExceptions(PqrNotFoundException exception, WebRequest webRequest){
        return response(exception, webRequest, HttpStatus.NOT_FOUND, exception.getMessage(), null);
    }

    @ExceptionHandler(RequiredValueException.class)
    public ResponseEntity<Object> handleRequiredValueExceptions(RequiredValueException exception, WebRequest webRequest){
        String message = "The "+exception.getMessage()+" is required";
        return response(exception, webRequest, HttpStatus.BAD_REQUEST, message, null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception,
                                                                        HttpHeaders headers, HttpStatus status,
                                                                        WebRequest request) {
        String errorMessage = new ArrayList<>(exception.getConstraintViolations()).get(0).getMessage();
        return response(exception, null, HttpStatus.BAD_REQUEST, errorMessage, null);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return response(ex, request, HttpStatus.BAD_REQUEST, "errorMessage", errors);
    }

    private ResponseEntity<Object> response(Exception ex, WebRequest request, HttpStatus status, String message, Map<String, String> errors) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(message);
        exceptionResponse.setDateTime(new Date());
        exceptionResponse.setStatus(status.value());
        exceptionResponse.setErrors(errors);
        return new ResponseEntity<Object>(exceptionResponse, status);
    }

}
