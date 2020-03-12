package com.ave.vehicleservice.api;

import com.ave.vehicleservice.domain.api.ExceptionResp;
import com.ave.vehicleservice.exception.BaseApiException;
import com.ave.vehicleservice.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<ExceptionResp> baseApiExceptionHandler(BaseApiException e) {
        ExceptionResp resp = ExceptionResp.from(e);
        return ResponseEntity.status(resp.getStatus()).body(resp);
    }
}
