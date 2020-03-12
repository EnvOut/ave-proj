package com.ave.vehicleservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AlreadyExists extends BaseApiException {
    private final Integer code = HttpStatus.CONFLICT.value();
    private final String message = "The same record already exists";
}
