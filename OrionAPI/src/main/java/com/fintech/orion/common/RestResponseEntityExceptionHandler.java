package com.fintech.orion.common;

import com.fintech.orion.dto.response.api.GenericErrorMessage;
import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.Principal;

/**
 * Created by sasitha on 12/27/16.
 *
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        ResponseEntity<Object> responseEntity = null;
        GenericErrorMessage errorMessage = new GenericErrorMessage();
        Principal principal = request.getUserPrincipal();
        LOGGER.error("Illegal sate/argument detected for a request sent buy client  {}  the original request was {}"
                , principal.getName(), request, ex);

        errorMessage.setMessage("Your request cause an illegal argument. Please check the request before re submitting");
        errorMessage.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        return new ResponseEntity<Object>(errorMessage,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = {FileUploadException.class})
    protected ResponseEntity<Object> handleFileUploadError(RuntimeException ex, WebRequest request){
        ResponseEntity<Object> responseEntity = null;
        GenericErrorMessage errorMessage = new GenericErrorMessage();
        Principal principal = request.getUserPrincipal();
        LOGGER.error("Unable to complete content upload by the client {} the original request was {} ",
                principal.getName(), request, ex);

        errorMessage.setMessage("Unable to complete the file upload. Please check the file size and file " +
                "type and try again");
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<Object>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    protected ResponseEntity<Object> handleNulPointerException(RuntimeException ex, WebRequest request){
        GenericErrorMessage errorMessage = new GenericErrorMessage();
        Principal principal = request.getUserPrincipal();
        LOGGER.error("Unexpected error occurred while try to process the request {} with by user {} ",
                request, principal.getName(), ex);
        errorMessage.setMessage("Could not process the given request. Please check the request and Try again");
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<Object>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
