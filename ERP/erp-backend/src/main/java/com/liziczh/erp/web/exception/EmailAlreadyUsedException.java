package com.liziczh.erp.web.exception;

public class EmailAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyUsedException(String message) {
        super(message);
    }

    public EmailAlreadyUsedException() {
        super("邮箱已被占用");
    }

}
