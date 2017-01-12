package com.fintech.orion.rest;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-18T09:12:11.427Z")

public class NotFoundException extends ApiException {
    private final int code;

    public NotFoundException(int code, String msg) {
        super(code, msg);
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
