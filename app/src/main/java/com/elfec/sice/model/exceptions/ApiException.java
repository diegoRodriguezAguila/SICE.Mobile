package com.elfec.sice.model.exceptions;

/**
 * Created by drodriguez on 06/07/2016.
 * Api related exception
 */
public class ApiException extends Exception {
    private String message;
    private int code;

    public ApiException() {
    }

    public ApiException(String message, int code) {
        super(message);
        this.code = code;
    }

    @Override
    public String getMessage(){
        return message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}