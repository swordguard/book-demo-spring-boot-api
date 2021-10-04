package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public final ValidationFailedResponse onConstraintValidationException(
            ConstraintViolationException ex) {
        ValidationFailedResponse error = new ValidationFailedResponse();
        for (ConstraintViolation<?> violation: ex.getConstraintViolations()) {
            error.getViolations().add(new ViolationErrors(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationFailedResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationFailedResponse error = new ValidationFailedResponse();
        for (FieldError fieldError: e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(new ViolationErrors(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return error;
    }
}
