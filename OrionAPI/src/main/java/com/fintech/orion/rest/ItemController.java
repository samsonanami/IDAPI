package com.fintech.orion.rest;

import com.fintech.orion.common.exceptions.DestinationProviderException;
import com.fintech.orion.common.exceptions.FileValidatorException;
import com.fintech.orion.common.exceptions.PersistenceException;
import com.fintech.orion.coreservices.ResourceServiceInterface;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.helper.GenerateUUID;
import com.fintech.orion.dataabstraction.models.verificationprocess.ProcessingRequest;
import com.fintech.orion.dto.resource.ResourceDTO;
import com.fintech.orion.dto.validation.file.ValidatorStatus;
import com.fintech.orion.handlers.OrionJobHandlerInterface;
import com.fintech.orion.helper.ErrorHandler;
import com.fintech.orion.helper.JsonValidatorInterface;
import com.fintech.orion.helper.ProcessingRequestHandlerInterface;
import com.fintech.orion.io.PersistenceInterface;
import com.fintech.orion.io.destination.DestinationProviderInterface;
import com.fintech.orion.model.ContentUploadResourceResult;
import com.fintech.orion.model.ResponseMessage;
import com.fintech.orion.model.VerificationResponseMessage;
import com.fintech.orion.validation.ClientValidatorInterface;
import com.fintech.orion.validation.file.FileValidatorInterface;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Principal;

/**
 * Item controller endpoints
 */
@Controller
public class ItemController {

    private static final String TAG = "ItemController: ";
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    public static final String SERVER_ERROR = "Server Error";

    @Autowired
    private String jsonNotInCorrectFormatMessage;

    @Autowired
    private ResourceServiceInterface resourceServiceInterface;

    @Autowired
    private JsonValidatorInterface jsonValidatorInterface;

    @Autowired
    private ProcessingRequestHandlerInterface processingRequestHandlerInterface;

    @Autowired
    private ClientValidatorInterface clientValidatorInterface;

    @Autowired
    private OrionJobHandlerInterface orionJobHandlerInterface;

    @Autowired
    private FileValidatorInterface fileValidator;

    @Autowired
    private PersistenceInterface localFilePersistence;

    @Autowired
    private DestinationProviderInterface genericDestinationProvider;

    @RequestMapping(value = "v1/content/{contentType}", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadContent(@PathVariable String contentType,
                                HttpServletResponse response, HttpServletRequest request,
                                @RequestParam("file") final MultipartFile multiPart) {
        try {

            Principal principal = request.getUserPrincipal();

            clientValidatorInterface.checkClientValidity(principal.getName());

            // content type validation must be implemented


            // file validation
            ValidatorStatus validatorStatus = fileValidator.validateFile(multiPart);

            if (!validatorStatus.isPassed()) {
                return ErrorHandler.renderError(HttpServletResponse.SC_BAD_REQUEST, validatorStatus.getMessage(), response);
            }

            String extension = FilenameUtils.getExtension(multiPart.getOriginalFilename());
            String uuidNumber = GenerateUUID.uuidNumber();
            String newFilename = uuidNumber + "." + extension;

            localFilePersistence.persistTo(multiPart, genericDestinationProvider.provide(newFilename));
            ResourceDTO resourceDTO = resourceServiceInterface.save(newFilename, uuidNumber, contentType, principal.getName());

            ContentUploadResourceResult result = new ContentUploadResourceResult();
            result.setResourceReferenceCode(resourceDTO.getResourceIdentificationCode());
            return result;

        } catch (ItemNotFoundException ex) {
            LOGGER.error(TAG, ex);
            return ErrorHandler.renderError(HttpServletResponse.SC_FORBIDDEN, "Unregistered access is not authorized.", response);
        } catch (FileValidatorException e) {
            LOGGER.error("provided multipart file was null", e);
            return ErrorHandler.renderError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, SERVER_ERROR, response);
        } catch (PersistenceException e) {
            LOGGER.error("persisting the file failed", e);
            return ErrorHandler.renderError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, SERVER_ERROR, response);
        } catch (DestinationProviderException e) {
            LOGGER.error("destination filename was null", e);
            return ErrorHandler.renderError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, SERVER_ERROR, response);
        }
    }

    @RequestMapping(value = "v1/verification", method = RequestMethod.POST)
    @ResponseBody
    public Object verification(@RequestParam(name = "integration_request", defaultValue = "false" ,required = false) boolean integrationRequest,
                               HttpServletResponse response, HttpServletRequest request,
                               @RequestBody ProcessingRequest data) {
        try {
            Principal principal = request.getUserPrincipal();

            clientValidatorInterface.checkClientValidity(principal.getName());

            if(!jsonValidatorInterface.jsonValidate(data.getVerificationProcesses())){
                return ErrorHandler.renderError(HttpServletResponse.SC_BAD_REQUEST, jsonNotInCorrectFormatMessage, response);
            }

            String processingRequestId = processingRequestHandlerInterface.saveVerificationProcessData(principal.getName(), data.getVerificationProcesses());

            if(!integrationRequest) {
                orionJobHandlerInterface.sendProcess(principal.getName(), processingRequestId);
            }

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
                                HttpServletResponse response, HttpServletRequest request
                                ) {
        try {
            Principal principal = request.getUserPrincipal();

            clientValidatorInterface.checkClientValidity(principal.getName());

            return processingRequestHandlerInterface.getVerificationRequestData(principal.getName(), verificationRequestId);
        } catch (Exception ex){
            LOGGER.error(TAG, ex);
            return ErrorHandler.renderError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage(), response);
        }
    }

    @RequestMapping(value = "v1/verification/{verificationProcessId}/resource/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void processedResources(@PathVariable String verificationProcessId,
                               @PathVariable String id,
                               HttpServletResponse response, HttpServletRequest request
                               ) throws IOException {
        ResponseMessage responseMessage;
        try {
            Principal principal = request.getUserPrincipal();

            clientValidatorInterface.checkClientValidity(principal.getName());

            BufferedImage bufferedImage = processingRequestHandlerInterface.getResourceData(principal.getName(), verificationProcessId, id);
            response.setContentType("image/jpg");
            ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
        } catch (Exception ex){
            LOGGER.error(TAG, ex);
            responseMessage = ErrorHandler.renderError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage(), response);
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":" + responseMessage.getStatus() + ",\"message\":\"" + responseMessage.getMessage() + "\"}");
        }
    }
}
