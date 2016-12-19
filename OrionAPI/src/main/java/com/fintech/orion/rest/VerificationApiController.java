package com.fintech.orion.rest;

import com.fintech.orion.api.service.client.ClientLicenseServiceInterface;
import com.fintech.orion.api.service.exceptions.ClientLicenseValidatorException;
import com.fintech.orion.api.service.exceptions.ClientServiceException;
import com.fintech.orion.api.service.request.ProcessingRequestServiceInterface;
import com.fintech.orion.api.service.validator.ClientLicenseValidatorServiceInterface;
import com.fintech.orion.api.service.validator.ProcessingRequestJsonFormatValidatorInterface;
import com.fintech.orion.dto.messaging.ProcessingMessage;
import com.fintech.orion.dto.request.api.VerificationRequest;
import com.fintech.orion.dto.response.api.GenericErrorMessage;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.dto.response.api.VerificationRequestResponse;
import com.fintech.orion.jobchanel.producer.MessageProducer;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-18T09:12:11.427Z")

@Controller
public class VerificationApiController implements VerificationApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(VerificationApiController.class);

    @Autowired
    private ClientLicenseServiceInterface clientService;

    @Autowired
    private ProcessingRequestJsonFormatValidatorInterface processingRequestJsonFormatValidator;

    @Autowired
    private ClientLicenseValidatorServiceInterface clientLicenseValidator;

    @Autowired
    private ProcessingRequestServiceInterface processingRequestHandlerInterface;

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private JmsTemplate jmsTemplate;

    public ResponseEntity<Object> verificationPost(
            @ApiParam(value = "", required = true, defaultValue = "true")
            @RequestParam(value = "integrationRequest", required = true, defaultValue="false") String integrationRequest,
            @ApiParam(value = "Processing request" ,required=true ) @RequestBody VerificationRequest body,
            HttpServletResponse response, HttpServletRequest request) {
        // do some magic!
        ResponseEntity<Object> responseEntity = null;
        GenericErrorMessage errorMessage = new GenericErrorMessage();
        Principal principal = request.getUserPrincipal();


        try {
            String licenseKey = clientService.getActiveLicenseOfClient(principal.getName());

            if(!processingRequestJsonFormatValidator.validate(body)){
                LOGGER.warn("Failed to validate processing request with json body {} by user {} ", body, principal.getName());
                errorMessage.setMessage("Invalid json format received. Please check the request and try again.");
                errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
                responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.BAD_REQUEST);
            }

            if(!clientLicenseValidator.validate(licenseKey, body)){
                LOGGER.warn("Client {} requested one or more processing type not coverd by the license with processing request {}", principal.getName(), body);
                errorMessage.setMessage("Your license does not cover the processing types requested");
                errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
                responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.UNAUTHORIZED);
            }

            String processingRequestId = processingRequestHandlerInterface.saveVerificationProcessData(principal.getName(), body.getVerificationProcesses());

            if (!Boolean.valueOf(integrationRequest)){
                ProcessingMessage message = new ProcessingMessage();
                message.setVerificationRequestCode(processingRequestId);
                message.setClientLicense(licenseKey);
                messageProducer.sendMessage(message, jmsTemplate);
            }

            VerificationRequestResponse verificationResponse = new VerificationRequestResponse();
            verificationResponse.setProcessingRequestId(processingRequestId);
            responseEntity = new ResponseEntity<Object>(verificationResponse, HttpStatus.OK);
        } catch (ClientServiceException e) {
            LOGGER.error("Could not find an active license key for the client with client name :" + principal.getName(), e);
            errorMessage.setMessage("Your license is expired or suspended. Please contact support");
            errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
            responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.UNAUTHORIZED);
        } catch (ClientLicenseValidatorException e) {
            LOGGER.error("Error in validating license of the client {}", principal.getName(), e);
            errorMessage.setMessage(e.getMessage());
            errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
            responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.UNAUTHORIZED);
        }

        return responseEntity;
    }

    public ResponseEntity<VerificationProcessDetailedResponse> verificationVerificationIdGet(@ApiParam(value = "verification id",required=true ) @PathVariable("verificationId") String verificationId) {
        // do some magic!
        return new ResponseEntity<VerificationProcessDetailedResponse>(HttpStatus.OK);
    }

}
