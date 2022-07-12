package com.fayardev.membershipsystem.exceptions.errors;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiError {

    private String timestamp;
    private HttpStatus status;
    private String message;
    private List<String> errors;
    private String errorCode;
    private String componentName;

    public ApiError(String timestamp, HttpStatus status, String message, List<String> errors, String errorCode) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.errorCode = errorCode;
    }

    public ApiError(String timestamp, HttpStatus status, String message, String error, String errorCode, String componentName) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errors = Arrays.asList(error);
        this.errorCode = errorCode;
        this.componentName = componentName;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }
}