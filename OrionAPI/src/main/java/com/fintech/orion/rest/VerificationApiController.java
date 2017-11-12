package com.fintech.orion.rest;

import com.fintech.orion.api.service.client.ClientLicenseServiceInterface;
import com.fintech.orion.api.service.exceptions.*;
import com.fintech.orion.api.service.request.ProcessingRequestServiceInterface;
import com.fintech.orion.api.service.validator.ClientLicenseValidatorServiceInterface;
import com.fintech.orion.api.service.validator.ProcessingRequestJsonFormatValidatorInterface;
import com.fintech.orion.api.service.validator.ResourceAccessValidator;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.messaging.ProcessingMessage;
import com.fintech.orion.dto.mrz.ScriptResult;
import com.fintech.orion.dto.mrz.ScriptResultReader;
import com.fintech.orion.dto.request.api.MRZRequest;
import com.fintech.orion.dto.request.api.Resource;
import com.fintech.orion.dto.request.api.VerificationDataRequest;
import com.fintech.orion.dto.request.api.VerificationProcess;
import com.fintech.orion.dto.request.api.VerificationRequest;
import com.fintech.orion.dto.response.api.GenericErrorMessage;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.dto.response.api.VerificationRequestResponse;
import com.fintech.orion.dto.response.api.VerificationRequestSummery;
import com.fintech.orion.dto.response.external.VerificationResponse;
import com.fintech.orion.jobchanel.producer.MessageProducer;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    private ResourceAccessValidator resourceAccessValidator;

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private JmsTemplate jmsTemplate;

    public ResponseEntity<Object> verificationPost(
            @ApiParam(value = "", required = true, defaultValue = "true")
            @RequestParam(value = "integration_request", required = true, defaultValue="false") String integrationRequest,
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
                return new ResponseEntity<Object>(errorMessage, HttpStatus.BAD_REQUEST);
            }

            if(!clientLicenseValidator.validate(licenseKey, body)){
                LOGGER.warn("Client {} requested one or more processing type not coverd by the license with processing request {}", principal.getName(), body);
                errorMessage.setMessage("Your license does not cover the processing types requested");
                errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
                return new ResponseEntity<Object>(errorMessage, HttpStatus.UNAUTHORIZED);
            }

            if (!resourceAccessValidator.validateResourceOwnership(principal.getName(), getResourceList(body))){
                LOGGER.warn("Client {} tried to access one or more resources not uploaded by them selfs.");
                errorMessage.setMessage("Unknown resource id was found in the processing request. " +
                        "Please check all the resource id's and try again");
                errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<Object>(errorMessage, HttpStatus.NOT_FOUND);
            }

            String processingRequestId = processingRequestHandlerInterface.saveVerificationProcessData(principal.getName(), body.getVerificationProcesses());

            updateMessageQueue(integrationRequest, licenseKey, processingRequestId);

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
        } catch (DataNotFoundException e) {
            LOGGER.error("Error ocured wihle processing the request {} submitted by user {}", body,
                    principal.getName(), e);
            errorMessage.setMessage(e.getMessage());
            errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
            responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    private void updateMessageQueue(String integrationRequest, String licenseKey, String processingRequestId) {
        if (!Boolean.valueOf(integrationRequest)){
            ProcessingMessage message = new ProcessingMessage();
            message.setVerificationRequestCode(processingRequestId);
            message.setClientLicense(licenseKey);
            messageProducer.sendMessage(message, jmsTemplate);
        }
    }

    public ResponseEntity<Object> verificationVerificationIdGet(
            @ApiParam(value = "verification id",required=true ) @PathVariable("verificationId") String verificationId,
            HttpServletResponse response, HttpServletRequest request) {
        VerificationResponse verificationResponse;
        ResponseEntity<Object> responseEntity =null;
        Principal principal = request.getUserPrincipal();
        GenericErrorMessage errorMessage = new GenericErrorMessage();
        try {
            clientService.getActiveLicenseOfClient(principal.getName());
            verificationResponse = processingRequestHandlerInterface.getDetailedResponse(principal.getName(), verificationId);
            responseEntity = new ResponseEntity<Object>(verificationResponse, HttpStatus.OK);
        } catch (ClientServiceException e) {
            LOGGER.error("Could not find an active license key for the client with client name :" + principal.getName(), e);
            errorMessage.setMessage("Your license is expired or suspended. Please contact support");
            errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
            responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.UNAUTHORIZED);
        } catch (IOException e) {
            LOGGER.error("Unable to parse json string to object ", e);
            errorMessage.setMessage("Internal server error. Please check your request and try agian");
            errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseEntity = new ResponseEntity<Object>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (ResourceAccessPolicyViolationException e) {
            LOGGER.warn("Client {} is trying to access resource {} which he/she is not autorized to ", principal.getName(), verificationId , e);
            errorMessage.setMessage("You are not authorized to access this resource. This attempt will be recored.");
            errorMessage.setStatus(HttpStatus.FORBIDDEN.value());
            responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.FORBIDDEN);
        } catch (ResourceNotFoundException e) {
            LOGGER.warn("Unable to find the verification request  {} mentioned by the client {} ", verificationId, principal.getName(), e);
            errorMessage.setMessage("The processing request you are trying to access is not found");
            errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
            responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }
    @Override
    public ResponseEntity<PagedResources<VerificationRequestSummery>> verificationRequestSummeryGet(
            @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "MM-dd-yyyy") Date from,
            @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "MM-dd-yyyy") Date to,
            @RequestParam(value = "page", required = false, defaultValue = "0") String pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") String pageSize,
            HttpServletRequest request, HttpServletResponse response) {
            Principal principal = request.getUserPrincipal();
        PagedResources<VerificationRequestSummery> pagedVerificationRequestSummery = null;
        try {
            pagedVerificationRequestSummery =  processingRequestHandlerInterface
                    .verificationRequestSummery(principal.getName(), from, to, pageNumber, pageSize);

        } catch (DataNotFoundException e) {
            LOGGER.warn("No history data found for the client {} ", principal.getName(), e);
        }
        return new ResponseEntity<PagedResources<VerificationRequestSummery>>(pagedVerificationRequestSummery, HttpStatus.OK);
    }

    private List<String> getResourceList(VerificationRequest verificationRequest){
        List<String> resourceIdList = new ArrayList<>();
        for (VerificationProcess verificationProcess : verificationRequest.getVerificationProcesses()){
            for (Resource resource : verificationProcess.getResources()){
                resourceIdList.add(resource.getResourceId());
            }
        }
        return resourceIdList;
    }

    /*
     * Update verification data controller
     */
    public ResponseEntity<Object> updateVerificationData(
            @ApiParam(value = "verification id", required = true) @PathVariable("verificationId") String verificationId,
            @ApiParam(value = "Processing request", required = true) @RequestBody VerificationResponse body,
            HttpServletResponse response, HttpServletRequest request) {
        ResponseEntity<Object> responseEntity = null;
        GenericErrorMessage errorMessage = new GenericErrorMessage();
        Principal principal = request.getUserPrincipal();
        try {
            clientService.getActiveLicenseOfClient(principal.getName());
            String processingRequestId = processingRequestHandlerInterface
                    .updateVerificationRequestData(principal.getName(), verificationId, body);
            VerificationRequestResponse verificationResponse = new VerificationRequestResponse();
            verificationResponse.setProcessingRequestId(processingRequestId);
            responseEntity = new ResponseEntity<Object>(verificationResponse, HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            LOGGER.error("Error ocured wihle processing the request {} submitted by user {}", body, principal.getName(),
                    e);
            errorMessage.setMessage(e.getMessage());
            errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
            responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.BAD_REQUEST);
        } catch (ClientServiceException e) {
            LOGGER.error("Could not find an active license key for the client with client name :" + principal.getName(),
                    e);
            errorMessage.setMessage("Your license is expired or suspended. Please contact support");
            errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
            responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.UNAUTHORIZED);
        } catch (JsonProcessingException e) {
            LOGGER.error("Erro occured while converting the request object to json string :" + principal.getName(), e);
            errorMessage.setMessage("Erro occured while converting the request object to json string ");
            errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
            responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    /*
     * Update re-verification data controller
     */
    public ResponseEntity<Object> updateReVerificationData(
            @ApiParam(value = "verification id", required = true) @PathVariable("verificationId") String verificationId,
            @ApiParam(value = "Processing request", required = true) @RequestBody VerificationDataRequest body,
            HttpServletResponse response, HttpServletRequest request) {
        ResponseEntity<Object> responseEntity = null;
        GenericErrorMessage errorMessage = new GenericErrorMessage();
        Principal principal = request.getUserPrincipal();
        try {
            String licenseKey = clientService.getActiveLicenseOfClient(principal.getName());
            clientService.getActiveLicenseOfClient(principal.getName());
            updateMessageQueueAboutReverification(licenseKey, verificationId, true, body);
            VerificationRequestResponse verificationResponse = new VerificationRequestResponse();
            verificationResponse.setProcessingRequestId(verificationId);
            responseEntity = new ResponseEntity<Object>(verificationResponse, HttpStatus.OK);
        } catch (ClientServiceException e) {
            LOGGER.error("Could not find an active license key for the client with client name :" + principal.getName(),
                    e);
            errorMessage.setMessage("Your license is expired or suspended. Please contact support");
            errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
            responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.UNAUTHORIZED);
        }
        return responseEntity;
    }

    /*
     * Update message queue with the processing request id and re-verification flag
     */
    private void updateMessageQueueAboutReverification(String licenseKey, String processingRequestId,
            boolean reVerification, VerificationDataRequest body) {
        ProcessingMessage message = new ProcessingMessage();
        message.setVerificationRequestCode(processingRequestId);
        message.setClientLicense(licenseKey);
        message.setReVerification(reVerification);
        /*
         * Also need to add VerificationDataRequest body to the message queue. Since we
         * have some pending things related to how to re-verify by skipping herms
         * request processor and only executing herms response processor, this will be
         * update later accordingly.
         */
        messageProducer.sendMessage(message, jmsTemplate);
    }

    /*
     * Generate mrz controller
     * 
     */
    public ResponseEntity<Object> generateMRZCode(
            @ApiParam(value = "Processing request", required = true) @RequestBody MRZRequest mrzRequestBody,
            HttpServletResponse response, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        GenericErrorMessage errorMessage = new GenericErrorMessage();
        ResponseEntity<Object> responseEntity = null;
        String cgiTestFileLocation = "";
        ScriptResult scriptResults = new ScriptResult();

        try {
            clientService.getActiveLicenseOfClient(principal.getName());
            /*
             * getClassLoader is not working for the wildfly deployment. By default it will
             * read the location as
             * [WildFlyHome]/bin/content/OrionAPI-0.1.0--.war/WEB-INF/classes/uniqueid_1.cgi
             * where there is no bin and content folders inside the wildfly. need to fix it
             * by following the other alternative solutions.
             */
            // String cgiTestFileLocation =
            // VerificationApiController.class.getClassLoader().getResource("uniqueid_1.cgi").getPath().substring(1);
            cgiTestFileLocation = "D:\\temp\\uniqueid_1.cgi";
            String[] commands = { "perl", "-wT", cgiTestFileLocation, "givennames=" + mrzRequestBody.getGivenNames(),
                    "surnames=" + mrzRequestBody.getSurNames(), "by=" + mrzRequestBody.getBirthYear(),
                    "bm=" + mrzRequestBody.getBirthMonth(), "bd=" + mrzRequestBody.getBirthDate(),
                    "sex=" + mrzRequestBody.getSex(), "issuer=" + mrzRequestBody.getIssuer(),
                    "ey=" + mrzRequestBody.getExpireYear(), "em=" + mrzRequestBody.getExpireMonth(),
                    "ed=" + mrzRequestBody.getExpireDate(), "passportnumber=" + mrzRequestBody.getPassportNum(),
                    "pin=" + mrzRequestBody.getPersonalNum(), "nationality=" + mrzRequestBody.getNationality(),
            "step=1" };

            Runtime rt = Runtime.getRuntime();
            ScriptResultReader scriptResultReader = new ScriptResultReader();
            Process proc = null;

            proc = rt.exec(commands);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String s = stdInput.readLine();
            while (stdInput.ready()) {
                s += stdInput.readLine();
            }
            scriptResults = scriptResultReader.read(s);
        } catch (IOException e) {
            LOGGER.error("IO Exception", e);
            errorMessage.setMessage("Error in reading perl script form the location : " + e.getMessage());
            errorMessage.setStatus(
                    HttpStatus.valueOf("Unable to read perl script file from : " + cgiTestFileLocation).value());
            responseEntity = new ResponseEntity<Object>(errorMessage,
                    HttpStatus.valueOf("Unable to read resource " + cgiTestFileLocation));
        } catch (ClientServiceException e) {
            LOGGER.error("Could not find an active license key for the client with client name :" + principal.getName(),
                    e);
            errorMessage.setMessage("Your license is expired or suspended. Please contact support");
            errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
            responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.UNAUTHORIZED);
        }
        responseEntity = new ResponseEntity<Object>(scriptResults, HttpStatus.OK);
        return responseEntity;

    }

}
