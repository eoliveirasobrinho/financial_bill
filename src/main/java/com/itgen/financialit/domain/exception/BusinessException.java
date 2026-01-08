package com.itgen.financialit.domain.exception;

public abstract class BusinessException extends RuntimeException{
    protected BusinessException(String message) {
        super(message);
    }
    
}
