package com.rmk.demo1;

import lombok.Getter;

public class DemoAppException extends Exception {

    String message;
    @Getter
    int errorCode;

    public DemoAppException(String msg) {
        this.message = msg;

    }
    public DemoAppException(String msg, int code) {
        this.message = msg;
        this.errorCode = code;
    }
    @Override
    public String getMessage(){
        return this.message;
    }

}


