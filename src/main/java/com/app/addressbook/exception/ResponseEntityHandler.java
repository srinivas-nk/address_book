package com.app.addressbook.exception;

import com.app.addressbook.model.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@Slf4j
@ControllerAdvice
public class ResponseEntityHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), HttpStatus.NOT_FOUND.value());
        log.error("Resource not found: {}", exceptionResponse);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        ExceptionResponse validationResponse = new ExceptionResponse(new Date(), ex.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity(validationResponse, HttpStatus.BAD_REQUEST);
    }
}
