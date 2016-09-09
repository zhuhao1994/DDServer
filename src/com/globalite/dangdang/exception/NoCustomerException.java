package com.globalite.dangdang.exception;

/**
 * Created by zhu on 2016/9/5.
 */
public class NoCustomerException extends Exception {
    private static final long serialVersionUID = 1976281070936534510L;

    public NoCustomerException() {
    }

    public NoCustomerException(String message) {
        super(message);
    }
}