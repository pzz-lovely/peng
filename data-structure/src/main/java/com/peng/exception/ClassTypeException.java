package com.peng.exception;

/**
 * @author lovely
 * @create 2021-03-08 11:22
 * @description
 */
public class ClassTypeException extends RuntimeException {
    public ClassTypeException() {
    }

    public ClassTypeException(String message) {
        super(message);
    }

    public ClassTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}