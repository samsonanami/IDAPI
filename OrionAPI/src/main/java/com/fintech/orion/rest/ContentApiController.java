package com.fintech.orion.rest;

import com.fintech.orion.api.service.client.ClientLicenseServiceInterface;
import com.fintech.orion.api.service.exceptions.ClientServiceException;
import com.fintech.orion.api.service.exceptions.FileValidatorException;
import com.fintech.orion.api.service.validator.file.FileValidatorInterface;
import com.fintech.orion.coreservices.ResourceServiceInterface;
import com.fintech.orion.dto.resource.ResourceDTO;
import com.fintech.orion.dto.response.api.GenericErrorMessage;
import com.fintech.orion.dto.response.api.ResourceUploadResponse;
import com.fintech.orion.dto.validation.file.ValidatorStatus;
import com.fintech.orion.exception.ResourceCreationException;
import com.fintech.orion.service.core.file.FileHandlerServiceInterface;
import com.fintech.orion.service.core.file.FileStorage;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-18T09:12:11.427Z")

@Controller
public class ContentApiController implements ContentApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentApiController.class);

    @Autowired
    private ClientLicenseServiceInterface clientService;
    @Autowired
    private FileValidatorInterface fileValidator;
    @Autowired
    private FileHandlerServiceInterface fileHandlerService;
    @Autowired
    private String workingDir;
    @Autowired
    private ResourceServiceInterface resourceServiceInterface;

    public ResponseEntity<Object> contentContentTypePost(
            @ApiParam(value = "type of the content you are submiting \"image\" \"video\" \"file\"",required=true )
            @PathVariable("contentType") String contentType,
            @ApiParam(value = "file detail") @RequestPart("file") MultipartFile file,
            HttpServletResponse response, HttpServletRequest request) {
        // do some magic!
        ResponseEntity<Object> responseEntity = null;
        GenericErrorMessage errorMessage = new GenericErrorMessage();
        Principal principal = request.getUserPrincipal();

        try {
            String licenseKey = clientService.getActiveLicenseOfClient(principal.getName());

            ValidatorStatus validatorStatus = fileValidator.validateFile(file);
            if (!validatorStatus.isPassed()) {
                errorMessage.setMessage(validatorStatus.getMessage());
                errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
                responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.UNAUTHORIZED);
            }

            String fileName = fileHandlerService.persistFile(file, FileStorage.LOCAL, workingDir);

            ResourceDTO resourceDTO = resourceServiceInterface.createResourceForUser(fileName, contentType, principal.getName());

            ResourceUploadResponse uploadResponse = new ResourceUploadResponse();
            uploadResponse.setResourceReferenceCode(resourceDTO.getResourceIdentificationCode());

            responseEntity = new ResponseEntity<Object>(uploadResponse, HttpStatus.OK);

        } catch (ClientServiceException e) {
            LOGGER.error("Could not find an active license key for the client with client name :" + principal.getName(), e);
            errorMessage.setMessage("Your license is expired or suspended. Please contact support");
            errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
            responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.UNAUTHORIZED);
        } catch (FileValidatorException e) {
            LOGGER.error("File Verification encountered an exeption", e);
            errorMessage.setMessage("Internal server error. Please check your request and try agian");
            errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseEntity = new ResponseEntity<Object>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            LOGGER.error("Unable to save the multipart file in location : " + workingDir, e);
            errorMessage.setMessage("Internal server error. Please check your request and try agian");
            errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseEntity = new ResponseEntity<Object>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ResourceCreationException e) {
            LOGGER.error("Unable to create resource with content type {} requested by user {} ",  contentType, principal.getName(), e);
            errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
            errorMessage.setMessage("Bad request : " + e.getMessage());
            responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

}
