package com.ave.vehicleservice.domain.api;

import com.ave.vehicleservice.exception.BaseApiException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ExceptionResp {
    private final Integer status;
    private final String message;

    public static ExceptionResp from(BaseApiException e) {
        return new ExceptionResp(e.getCode(), e.getMessage());
    }
}
