package com.fintech.orion.rest;

import com.fintech.orion.coreservices.ResourceServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.verificationprocess.ProcessingRequest;
import com.fintech.orion.helper.*;
import com.fintech.orion.dataabstraction.helper.GenerateUUID;
import com.fintech.orion.model.ContentUploadResourceResult;
import com.fintech.orion.model.ResponseMessage;
import com.fintech.orion.model.VerificationResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

@Controller
public class ItemController {

    private static final String TAG = "ItemController: ";
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private String fileNotInCorrectFormatMessage;

    @Autowired
    private String maximumFileSizeMessage;

    @Autowired
    private String jsonNotInCorrectFormatMessage;

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

    @Autowired
    private ClientValidatorInterface clientValidatorInterface;

    @RequestMapping(value = "v1/content/{contentType}", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadContent(@PathVariable String contentType,
                                HttpServletResponse response,
                                @RequestParam("access_token") String accessToken,
                                @RequestParam("file") final MultipartFile multiPart) {
        try {
            clientValidatorInterface.checkClientValidity(accessToken);

            Resource resource;
            String fileName = multiPart.getOriginalFilename();
            String extension = fileName.split("\\.")[1];
            String uuidNumber = GenerateUUID.uuidNumber();
            String newFilename = uuidNumber + "." + extension;
            if (!fileExtensionValidatorInterface.validate(extension)) {
                return ErrorHandler.renderError(HttpServletResponse.SC_BAD_REQUEST, fileNotInCorrectFormatMessage, response);
            }

            boolean isUploaded = fileUploadHandlerInterface.upload(multiPart, newFilename);

            if(isUploaded) {
                resource = resourceServiceInterface.save(newFilename, uuidNumber, contentType, accessToken);
            } else {
                return ErrorHandler.renderError(HttpServletResponse.SC_BAD_REQUEST, maximumFileSizeMessage, response);
            }
            ContentUploadResourceResult result = new ContentUploadResourceResult();
            result.setResourceReferenceCode(resource.getResourceIdentificationCode());
            return result;
        } catch (ItemNotFoundException ex) {
            LOGGER.error(TAG, ex);
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
            clientValidatorInterface.checkClientValidity(accessToken);

            if(!jsonValidatorInterface.jsonValidate(data.getVerificationProcesses())){
                return ErrorHandler.renderError(HttpServletResponse.SC_BAD_REQUEST, jsonNotInCorrectFormatMessage, response);
            }

            String processingRequestId = processingRequestHandlerInterface.saveVerificationProcessData(accessToken, data.getVerificationProcesses());

            VerificationResponseMessage result = new VerificationResponseMessage();
            result.setProcessingRequestId(processingRequestId);
            return result;
        } catch (Exception ex){
            LOGGER.error(TAG, ex);
            return ErrorHandler.renderError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage(), response);
        }
    }

    @RequestMapping(value = "v1/verification/{verificationRequestId}", method = RequestMethod.GET)
    @ResponseBody
    public Object verificationResults(@PathVariable String verificationRequestId,
                                HttpServletResponse response,
                                @RequestParam("access_token") String accessToken) {
        try {
            clientValidatorInterface.checkClientValidity(accessToken);

            return processingRequestHandlerInterface.getVerificationRequestData(accessToken, verificationRequestId);
        } catch (Exception ex){
            LOGGER.error(TAG, ex);
            return ErrorHandler.renderError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage(), response);
        }
    }

    @RequestMapping(value = "v1/verification/{verificationProcessId}/resource/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void processedResources(@PathVariable String verificationProcessId,
                               @PathVariable String id,
                               HttpServletResponse response,
                               @RequestParam("access_token") String accessToken) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            //clientValidatorInterface.checkClientValidity(accessToken);

            BufferedImage bufferedImage = processingRequestHandlerInterface.getResourceData(accessToken, verificationProcessId, id);
            response.setContentType("image/jpg");
            ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
        } catch (Exception ex){
            LOGGER.error(TAG, ex);
            responseMessage = ErrorHandler.renderError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage(), response);
        }
        response.setContentType("application/json");
        try {
            response.getWriter().write("{\"status\":" + responseMessage.getStatus() + ",\"message\":\"" + responseMessage.getMessage() + "\"}");
        } catch (Exception e1) {
            LOGGER.error(TAG, e1);
            ErrorHandler.renderError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "internal server error", response);
        }
    }
}
