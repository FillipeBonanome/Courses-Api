package com.courses.api.Api.infra.exception;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
