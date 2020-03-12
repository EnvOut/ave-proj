package com.ave.vehicleservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ValidationException extends BaseApiException {
    private final String message = "Request is not valid";
    private final Integer code = HttpStatus.BAD_REQUEST.value();
}
