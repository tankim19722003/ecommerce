package ecommerce.example.ecommerce.Exceptions;

import ecommerce.example.ecommerce.responses.EResponse;

import java.util.List;

public class ValidationException extends RuntimeException{
    private final List<EResponse> errors;

    public ValidationException(List<EResponse> errors) {
        super("Validation failed");
        this.errors = errors;
    }

    public List<EResponse> getErrors() {
        return errors;
    }
}
