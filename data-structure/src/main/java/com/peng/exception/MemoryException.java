package com.peng.exception;

/**
 * @author lovely
 * @create 2021-03-06 9:54
 * @description
 */
public class MemoryException extends RuntimeException{
    public MemoryException() {
    }

    public MemoryException(String message) {
        super(message);
    }

    public MemoryException(String message, Throwable cause) {
        super(message, cause);
    }
}