package com.williams.studentrecord.exception;

public class NotFoundException extends AbstractException{

    public NotFoundException(String code, String message) {
        super(code, message);
    }
}
