package com.fintech.orion.rest;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-18T09:12:11.427Z")

public class ApiException extends Exception {
    private final int code;

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
