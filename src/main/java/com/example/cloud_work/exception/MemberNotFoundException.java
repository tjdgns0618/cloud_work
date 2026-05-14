package com.example.cloud_work.exception;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends ServiceException {
    public MemberNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
