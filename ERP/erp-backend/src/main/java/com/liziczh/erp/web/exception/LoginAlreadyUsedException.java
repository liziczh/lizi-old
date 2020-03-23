package com.liziczh.erp.web.exception;

public class LoginAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LoginAlreadyUsedException(String message) {
        super(message);
    }

    public LoginAlreadyUsedException() {
        super("用户名已占用");
    }

}
