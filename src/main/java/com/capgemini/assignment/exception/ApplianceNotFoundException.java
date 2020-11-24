package com.capgemini.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApplianceNotFoundException extends RuntimeException {

    public ApplianceNotFoundException(String exception) {
        super(exception);
    }

}
