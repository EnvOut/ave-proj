package com.ave.vehicleservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFound extends BaseApiException {
    private final Integer code = HttpStatus.NOT_FOUND.value();
    private final String message = "Resource with that id does not exist";
}
