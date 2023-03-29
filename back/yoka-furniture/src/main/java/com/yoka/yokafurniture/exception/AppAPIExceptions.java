package com.yoka.yokafurniture.exception;

import org.springframework.http.HttpStatus;

public class AppAPIExceptions extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public AppAPIExceptions(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public AppAPIExceptions(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
