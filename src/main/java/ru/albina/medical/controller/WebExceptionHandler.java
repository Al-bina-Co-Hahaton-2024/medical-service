package ru.albina.medical.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.albina.medical.dto.response.ErrorDto;
import ru.albina.medical.exception.EntityNotFoundException;

@RestControllerAdvice
public class WebExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class})
    public ErrorDto notFound(Exception e) {
        return new ErrorDto(e.getMessage());
    }

}
