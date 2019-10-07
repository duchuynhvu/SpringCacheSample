
package com.example.springcache.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    private static final long serialVersionUID = -8903075067114715153L;

    /**
     * Instantiates a new Resource not found exception.
     *
     * @param message
     *            the message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String entity, Long id) {
        super(entity + "id" + " not found");
    }
}
