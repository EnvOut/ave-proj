package com.ave.vehicleservice.exception;

public abstract class BaseApiException extends RuntimeException {

    public abstract String getMessage();

    public abstract Integer getCode();
}

