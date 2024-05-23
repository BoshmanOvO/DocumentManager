package org.micro.documentmanager.Exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
    public ApiException() {
        super("An error occurred. Please try again later.");
    }
}
