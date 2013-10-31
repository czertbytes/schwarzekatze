package de.czertbytes.schwarzekatze.api.controller;

import de.czertbytes.schwarzekatze.api.domain.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

public abstract class BaseController {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleMethodArgumentNotValidException(MethodArgumentNotValidException error) {
        return new Error(HttpStatus.BAD_REQUEST.value(), "Request content is not valid!");
    }

    @ExceptionHandler(value = EntityExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleEntityExistsException() {
        return new Error(HttpStatus.BAD_REQUEST.value(), "Request content is not valid!");
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public Error handleEntityNotFoundException() {
        return new Error(HttpStatus.NOT_FOUND.value(), "Requested entity not fond!");
    }

}
