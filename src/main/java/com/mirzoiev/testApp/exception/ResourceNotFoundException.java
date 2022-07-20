package com.mirzoiev.testApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom Resource Not Found Exception class
 * extends Runtime Exception
 *
 * @author R.M.
 * @since 15.07.2022
 * @see java.lang.Exception
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1l;

    public ResourceNotFoundException(String message) {
        super(message);
    }


}
