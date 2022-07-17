package com.fayardev.regms.exceptions;

import com.fayardev.regms.exceptions.enums.ErrorComponents;
import com.fayardev.regms.exceptions.enums.Errors;

public class ProfileException extends Exception {

    public static final String NAME = "ProfileException";
    private final String message;
    private ErrorComponents errorComponent;
    private Errors error;

    public ProfileException(String message) {
        this.message = message;
    }

    public ProfileException(String message, Errors error, ErrorComponents errorComponent) {
        this.message = message;
        this.error = error;
        this.errorComponent = errorComponent;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public Errors getError() {
        return error;
    }

    public void setError(Errors error) {
        this.error = error;
    }

    public ErrorComponents getErrorComponent() {
        return errorComponent;
    }

    public void setErrorComponent(ErrorComponents errorComponent) {
        this.errorComponent = errorComponent;
    }
}