package com.poker.texas_holdem.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.poker.texas_holdem.dtos.errors.BaseErrorResponse;
import com.poker.texas_holdem.dtos.errors.ErrorResponse;
import com.poker.texas_holdem.dtos.errors.ErrorsResponse;
import com.poker.texas_holdem.utils.exceptions.BadRequestException;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ErrorController {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public BaseErrorResponse errorsResponse(MethodArgumentNotValidException exception) {
    List<String> errors = new ArrayList<>();

    exception.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));

    return ErrorsResponse.builder().errors(errors).status(HttpStatus.BAD_REQUEST.name())
        .code(HttpStatus.BAD_REQUEST.value()).build();
  }

  @ExceptionHandler(BadRequestException.class)
  public BaseErrorResponse badRequest(BadRequestException exception) {

    return ErrorResponse.builder().message(exception.getMessage()).status(HttpStatus.BAD_REQUEST.name())
        .code(HttpStatus.BAD_REQUEST.value()).build();
  }
}
