package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.responses.EResponse;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ValidataDataService {
    public static List<EResponse> handleFieldError(BindingResult result) {
            List<EResponse> errorMessages = new ArrayList<>();
            if (result.hasErrors()) {
                errorMessages = result.getFieldErrors()
                        .stream()
                        .map(fieldError -> EResponse.builder()
                                .name(fieldError.getField())
                                .message(fieldError.getDefaultMessage())
                                .build()
                        ).collect(Collectors.toList());
                return errorMessages;
            }
            return errorMessages;
    }
}
