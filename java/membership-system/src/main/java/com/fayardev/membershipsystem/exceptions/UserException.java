package com.fayardev.membershipsystem.exceptions;

import com.fayardev.membershipsystem.exceptions.enums.ErrorComponents;

public class UserException extends Exception {

    public static final String NAME = "UserException";
    private final String message;
    private ErrorComponents errorComponent;
    private Error error;

    public UserException(String message) {
        this.message = message;
    }

    public UserException(String message, Error error, ErrorComponents errorComponent) {
        this.message = message;
        this.error = error;
        this.errorComponent = errorComponent;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public ErrorComponents getErrorComponent() {
        return errorComponent;
    }

    public void setErrorComponent(ErrorComponents errorComponent) {
        this.errorComponent = errorComponent;
    }
}
