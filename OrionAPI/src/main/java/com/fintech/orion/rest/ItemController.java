package com.fintech.orion.rest;

import com.fintech.orion.coreservices.ResourceServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.verificationprocess.ProcessingRequest;
import com.fintech.orion.helper.*;
import com.fintech.orion.dataabstraction.helper.GenerateUUID;
import com.fintech.orion.model.ContentUploadResourceResult;
import com.fintech.orion.model.VerificationResponseMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ItemController {

    private static final Logger LOGGER = Logger.getLogger(ItemController.class);

    private static final String THE_SUBMITTED_FILE_IS_NOT_IN_CORRECT_FORMAT = "The submitted file is not in correct format";
    private static final String FILE_UPLOAD_ERROR = "File Upload Error";
    private static final String JSON_NOT_IN_CORRECT_FORMAT = "Json not in correct format";

    @Autowired
    private ResourceServiceInterface resourceServiceInterface;

    @Autowired
    private FileExtensionValidatorInterface fileExtensionValidatorInterface;

    @Autowired
    private FileUploadHandlerInterface fileUploadHandlerInterface;

    @Autowired
    private JsonValidatorInterface jsonValidatorInterface;

    @Autowired
    private ProcessingRequestHandlerInterface processingRequestHandlerInterface;

    @RequestMapping(value = "v1/content/{contentType}", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadContent(@PathVariable String contentType,
                                HttpServletResponse response,
                                @RequestParam("access_token") String accessToken,
                                @RequestParam("file") final MultipartFile multiPart) {
        try {
            Resource resource;
            String fileName = multiPart.getOriginalFilename();
            String extension = fileName.split("\\.")[1];
            String uuidNumber = GenerateUUID.uuidNumber();
            String newFilename = uuidNumber + "." + extension;
            if (!fileExtensionValidatorInterface.validate(extension)) {
                return ErrorHandler.renderError(HttpServletResponse.SC_BAD_REQUEST, THE_SUBMITTED_FILE_IS_NOT_IN_CORRECT_FORMAT, response);
            }

            boolean isSaved = fileUploadHandlerInterface.upload(multiPart, newFilename);

            if(isSaved) {
                resource = resourceServiceInterface.saveResource(newFilename, uuidNumber, contentType, accessToken);
            } else {
                return ErrorHandler.renderError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, FILE_UPLOAD_ERROR, response);
            }
            ContentUploadResourceResult result = new ContentUploadResourceResult();
            result.setResourceReferenceCode(resource.getResourceIdentificationCode());
            return result;
        } catch (ItemNotFoundException ex) {
            LOGGER.error(ex);
            LOGGER.error(ex.getMessage());
            return ErrorHandler.renderError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage(), response);
        }
    }

    @RequestMapping(value = "v1/verification", method = RequestMethod.POST)
    @ResponseBody
    public Object verification(@RequestParam(name = "integration_request", required = false) boolean integrationRequest,
                               HttpServletResponse response,
                               @RequestParam("access_token") String accessToken,
                               @RequestBody ProcessingRequest data) {
        try {
            if(!jsonValidatorInterface.jsonValidate(data.getVerificationProcesses())){
                return ErrorHandler.renderError(HttpServletResponse.SC_BAD_REQUEST, JSON_NOT_IN_CORRECT_FORMAT, response);
            }

            String processingRequestId = processingRequestHandlerInterface.saveData(accessToken, data.getVerificationProcesses());

            VerificationResponseMessage result = new VerificationResponseMessage();
            result.setProcessingRequestId(processingRequestId);
            return result;
        } catch (Exception ex){
            LOGGER.error(ex);
            LOGGER.error(ex.getMessage());
            return ErrorHandler.renderError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage(), response);
        }
    }

    @RequestMapping(value = "v1/verification/{verificationRequestId}", method = RequestMethod.GET)
    @ResponseBody
    public Object verificationResults(@PathVariable String verificationRequestId,
                                HttpServletResponse response,
                                @RequestParam("access_token") String accessToken) {
        try {
            return processingRequestHandlerInterface.getData(verificationRequestId);
        } catch (Exception ex){
            LOGGER.error(ex);
            LOGGER.error(ex.getMessage());
            return ErrorHandler.renderError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage(), response);
        }
    }

    @RequestMapping(value = "v1/verification/{verificationProcessId}/resource/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object processedResources(@PathVariable String verificationProcessId,
                               @PathVariable int id,
                               HttpServletResponse response,
                               @RequestParam("access_token") String accessToken) {
        try {
            // TODO
            return null;
        } catch (Exception ex){
            throw ex;
        }
    }
}
