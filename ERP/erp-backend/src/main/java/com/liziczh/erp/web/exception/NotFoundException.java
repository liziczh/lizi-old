package com.liziczh.erp.web.exception;

/**
 * @author Lizi
 */
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super("NotFound");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
