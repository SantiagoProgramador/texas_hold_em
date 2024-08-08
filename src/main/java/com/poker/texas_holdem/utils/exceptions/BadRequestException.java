package com.poker.texas_holdem.utils.exceptions;

public class BadRequestException extends RuntimeException {
  
  public BadRequestException(String error) {
    super(error);
  }
}
