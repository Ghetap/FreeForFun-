package com.example.freeforfun.freeforfun.business.exceptions;

import com.example.freeforfun.freeforfun.persistence.exceptions.RepositoryException;

public class BusinessException extends Exception{

    public BusinessException(RepositoryException e) {
        super(e);
        this.errorCode = e.getErrorCode();

    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    private String errorCode;

    public BusinessException(String s,String errorCode) {
        super(s);
        this.errorCode = errorCode;
    }

    public BusinessException(String s, Throwable throwable) {
        super(s, throwable);
    }

    @Override
    public String toString() {
        return super.getMessage() + ": " + " " + this.errorCode;
    }
}
