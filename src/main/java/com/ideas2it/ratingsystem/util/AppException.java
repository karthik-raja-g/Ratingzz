package com.ideas2it.ratingsystem.util;

public class AppException extends Exception {
  String errorMessage;

  public AppException(String message) {
    errorMessage = message;
  }
  public String toString() {
    return errorMessage;
  }
}
