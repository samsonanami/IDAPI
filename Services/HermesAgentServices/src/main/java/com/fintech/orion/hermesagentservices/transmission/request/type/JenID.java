package com.fintech.orion.hermesagentservices.transmission.request.type;

import com.fintech.orion.common.exceptions.request.RequestException;
import com.fintech.orion.coreservices.ProcessingStatusServiceInterface;
import com.fintech.orion.dataabstraction.models.Status;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.hermesagentservices.state.process.ProcessStateInterface;
import com.fintech.orion.hermesagentservices.transmission.payload.processor.ProcessorInterface;
import com.fintech.orion.hermesagentservices.transmission.request.basetype.RequestCreatorInterface;
import com.fintech.orion.hermesagentservices.transmission.request.body.BodyServiceInterface;
import com.fintech.orion.hermesagentservices.transmission.request.submit.RequestSubmitterInterface;
import com.fintech.orion.hermesagentservices.transmission.response.handler.ResponseHandlerInterface;
import com.fintech.orion.hermesagentservices.transmission.response.handler.license.LicenseHandlerInterface;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.request.BaseRequest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TharinduMP on 10/13/2016.
 * JenID API Request Implementation
 */
@Component
@Scope("prototype")
public class JenID extends AbstractRequest implements RequestInterface {

    static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JenID.class);

    @Autowired
    private BodyServiceInterface jenIdBody;

    @Autowired
    private RequestCreatorInterface jenIdPostSyncRequest;

    @Autowired
    private RequestSubmitterInterface requestSubmitter;

    @Autowired
    private ResponseHandlerInterface jenIdResponseHandler;

    @Autowired
    private ProcessingStatusServiceInterface processingStatusService;

    @Autowired
    private LicenseHandlerInterface licenseHandler;

    @Autowired
    private ProcessStateInterface processState;

    @Autowired
    private ProcessorInterface jenIdProcessor;

    @Transactional
    @Override
    public void process(GenericRequest genericRequest) throws RequestException {

        // initialize the resources and configurations
        super.process(genericRequest);

        Map<String, Object> extras = new HashMap<>();

        try {
            //start pessimistic. set status to failed.
            processDTO.setProcessingStatusDTO(processingStatusService.findByStatus(Status.PROCESSING_FAILED));

            // create jen id body
            Object o = jenIdBody.createJSONBody(processConfigurationMap, resourceList, null);
            extras.put("body", jenIdProcessor.marshall(o));

            // create request
            BaseRequest request = jenIdPostSyncRequest.createRequest(processConfigurationMap, resourceList, extras);

            //set request start time
            processDTO.setRequestSentOn(new Date());

            // make jen id call
            HttpResponse<String> response = requestSubmitter.submitRequest(request);

            //set response arrival time
            processDTO.setResponseReceivedOn(new Date());

            // handle response and status change to complete
            this.processDTO = jenIdResponseHandler.handleResponse(response, processDTO);

        } catch (Exception e) {
            LOGGER.error("JenID Request Failed. Status Set to failed", e);
            throw new RequestException(e);
        } finally {
            // save process with status
            processState.updateFinalProcessState(processDTO);

            //update license
            licenseHandler.updateLicense(processDTO, genericRequest);
        }

    }
}
