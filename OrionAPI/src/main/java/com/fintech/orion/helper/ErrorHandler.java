package com.fintech.orion.helper;

import com.fintech.orion.model.ResponseMessage;

import javax.servlet.http.HttpServletResponse;

public class ErrorHandler {

    private ErrorHandler() {}

    public static ResponseMessage renderError(int errorCode, String message, HttpServletResponse response) {
        response.setStatus(errorCode);
        ResponseMessage messageObj = new ResponseMessage();
        messageObj.setStatus(errorCode);
        messageObj.setMessage(message);
        return messageObj;

    }
}
