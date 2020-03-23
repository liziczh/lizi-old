package com.liziczh.erp.web.exception;

public class LowInStockException extends RuntimeException  {
    private static final long serialVersionUID = 1L;

    public LowInStockException() {
        super("LowInStock");
    }

    public LowInStockException(String message) {
        super(message);
    }
}
