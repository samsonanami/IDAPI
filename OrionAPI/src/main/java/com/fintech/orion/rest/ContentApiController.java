package com.fintech.orion.rest;

import com.fintech.orion.api.service.client.ClientLicenseServiceInterface;
import com.fintech.orion.api.service.exceptions.ClientServiceException;
import com.fintech.orion.api.service.validator.ResourceAccessValidator;
import com.fintech.orion.coreservices.ResourceServiceInterface;
import com.fintech.orion.coreservices.ResourceTypeServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.resource.ResourceDTO;
import com.fintech.orion.dto.response.api.GenericErrorMessage;
import com.fintech.orion.dto.response.api.ResourceUploadResponse;
import com.fintech.orion.dto.validation.file.ValidatorStatus;
import com.fintech.orion.exception.BusinessException;
import com.fintech.orion.exception.ResourceCreationException;
import com.fintech.orion.resource.Resource;
import com.fintech.orion.resource.builder.ResourceBuilder;
import com.fintech.orion.resource.persistence.exception.PersistenceException;
import com.fintech.orion.resource.response.ResourceResponse;
import com.fintech.orion.resource.upload.UploadResource;

import io.swagger.annotations.ApiParam;

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

import java.security.Principal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.io.File;
import java.io.IOException;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-18T09:12:11.427Z")

@Controller
public class ContentApiController implements ContentApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentApiController.class);

    @Autowired
    private ClientLicenseServiceInterface clientService;
    @Autowired
    private ResourceServiceInterface resourceServiceInterface;
    @Autowired
    private ResourceTypeServiceInterface resourceTypeServiceInterface;
    @Autowired
    private String workingDir;
    @Autowired
    private ResourceAccessValidator resourceAccessValidator;
    @Autowired
    private ResourceBuilder resourceBuilder;

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
            clientService.getActiveLicenseOfClient(principal.getName());

            UploadResource uploadResource = new UploadResource(file, contentType);
            Resource resource = resourceBuilder.build(uploadResource);
            ValidatorStatus status = resource.validate();

            if(status.isPassed()) {
                String fileName = resource.persist();
                ResourceDTO resourceDTO = resourceServiceInterface.createResourceForUser(fileName, contentType, principal.getName());
                ResourceUploadResponse uploadResponse = new ResourceUploadResponse();
                uploadResponse.setResourceReferenceCode(resourceDTO.getResourceIdentificationCode());
                responseEntity = new ResponseEntity<Object>(uploadResponse, HttpStatus.OK);

            } else
            {
                errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
                errorMessage.setMessage(status.getMessage());
                responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.BAD_REQUEST);
            }

        } catch (ClientServiceException e) {
            LOGGER.error("Could not find an active license key for the client with client name :" + principal.getName(), e);
            errorMessage.setMessage("Your license is expired or suspended. Please contact support");
            errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
            responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.UNAUTHORIZED);
        }catch (ResourceCreationException e) {
            LOGGER.error("Unable to create resource with content type {} requested by user {} ",  contentType, principal.getName(), e);
            errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
            errorMessage.setMessage("Bad request : " + e.getMessage());
            responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.BAD_REQUEST);
        } catch (PersistenceException e) {
            LOGGER.error("Unable to Persist Resource", e);
            errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorMessage.setMessage("Internal Server Error");
            responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (BusinessException e) {
            LOGGER.warn("Business Exception", e);
            errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
            errorMessage.setMessage("Bad request : " + e.getMessage());
            responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    public ResponseEntity<Object> getResourceByResourceId(
            @ApiParam(value = "resource id", required = true) @PathVariable("resourceId") String resourceId,
            HttpServletResponse response, HttpServletRequest request) {
        ResponseEntity<Object> responseEntity = null;
        GenericErrorMessage errorMessage = new GenericErrorMessage();
        Principal principal = request.getUserPrincipal();
        if (!resourceAccessValidator.validateResourceOwnership(principal.getName(), getResourceList(resourceId))) {
            LOGGER.warn("Client {} tried to access one or more resources not uploaded by them selfs.");
            errorMessage.setMessage("Unknown resource id was found in the processing request. "
                    + "Please check all the resource id's and try again");
            errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<Object>(errorMessage, HttpStatus.NOT_FOUND);
        }
        try {
            com.fintech.orion.dataabstraction.entities.orion.Resource resource = resourceServiceInterface
                    .getResourceByResourceId(resourceId);
            ResourceType resourceType = resourceTypeServiceInterface.findById(resource.getResourceType().getId());
            File ImgFile = new File(workingDir + resource.getLocation());
            byte[] byteArrayResource = Files.readAllBytes(ImgFile.toPath());
            ResourceResponse resourceResponse = new ResourceResponse();
            resourceResponse.setContent(Base64.getEncoder().encodeToString(byteArrayResource));
            resourceResponse.setContentType(resourceType.getType());
            responseEntity = new ResponseEntity<Object>(resourceResponse, HttpStatus.OK);
        } catch (BusinessException e) {
            LOGGER.warn("Business Exception", e);
            errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
            errorMessage.setMessage("Bad request : " + e.getMessage());
            responseEntity = new ResponseEntity<Object>(errorMessage, HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            LOGGER.error("IO Exception", e);
            errorMessage.setMessage("Error in reading resource form the location : " + e.getMessage());
            errorMessage.setStatus(HttpStatus.valueOf("Unable to read resource " + resourceId).value());
            responseEntity = new ResponseEntity<Object>(errorMessage,
                    HttpStatus.valueOf("Unable to read resource " + resourceId));
        } catch (ItemNotFoundException e) {
            LOGGER.error("Item Not Found Exception", e);
            errorMessage.setMessage("Error in reading resource with the id " + resourceId);
            errorMessage.setStatus(HttpStatus.valueOf("Unable to find resource " + resourceId).value());
            responseEntity = new ResponseEntity<Object>(errorMessage,
                    HttpStatus.valueOf("Unable to find resource " + resourceId));
        }

        return responseEntity;
    }

    private List<String> getResourceList(String resourceId) {
        List<String> resourceIdList = new ArrayList<>();
        resourceIdList.add(resourceId);
        return resourceIdList;
    }

}
