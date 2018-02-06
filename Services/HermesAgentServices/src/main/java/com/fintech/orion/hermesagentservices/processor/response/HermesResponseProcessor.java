package com.fintech.orion.hermesagentservices.processor.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.exceptions.transformer.RequestTransformerException;
import com.fintech.orion.dto.hermese.ResponseProcessorResult;
import com.fintech.orion.dto.response.api.FieldData;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.dto.response.external.Data;
import com.fintech.orion.dto.response.external.DataValues;
import com.fintech.orion.dto.response.external.VerificationResponse;
import com.fintech.orion.hermesagentservices.processor.VerificationResult;
import com.fintech.orion.hermesagentservices.processor.request.HermeseRequestProcessor;
import com.fintech.orion.hermesagentservices.processor.response.chain.ResponseProcessorChainHead;
import com.fintech.orion.hermesagentservices.transformer.ResponseTransformer;
import com.fintech.orion.hermesagentservices.transformer.factory.ResponseTransformerFactory;
import com.fintech.orion.hermesagentservices.transformer.mapper.DataMapper;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;



public class HermesResponseProcessor implements HermesResponseProcessorInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(HermeseRequestProcessor.class);
    private static final String STATUS_FAILED = "failed";

    @Autowired
    private ResponseProcessorChainHead hermesResponseProcessorChainHead;

    @Autowired
    private ResponseProcessorChainHead hermeseReVerificationProcessorChainHead;

    @Autowired
    private ResponseTransformerFactory responseTransformerFactory;

    @Autowired
    private String verificationStatusSuccess;

    @Autowired
    private String verificationStatusFail;

    @Override
    public ResponseProcessorResult processVerificationResults(List<VerificationResult> verificationResults,
                                                              String processingRequestId, boolean isReVerification,
                                                              String reVerificationStatus) {
        ResponseProcessorResult result = new ResponseProcessorResult();
        ObjectMapper objectMapper = new ObjectMapper();
        VerificationProcessDetailedResponse verificationProcessDetailedResponse =
                new VerificationProcessDetailedResponse();
        VerificationResponse verificationResponse = new VerificationResponse();
        verificationProcessDetailedResponse.setVerificationRequestId(processingRequestId);


        if(isReVerification){
            hermeseReVerificationProcessorChainHead.start(verificationProcessDetailedResponse,
                    verificationResults, processingRequestId);
        }else {
            hermesResponseProcessorChainHead.start(verificationProcessDetailedResponse,
                    verificationResults, processingRequestId);
        }

        try {
            ResponseTransformer responseTransformer =
                    responseTransformerFactory.getResponseTransformer("defaultResponseTransformer");
            verificationResponse = (VerificationResponse)responseTransformer.transform(verificationProcessDetailedResponse, isReVerification, reVerificationStatus);
            result.setProcessedString(objectMapper.writeValueAsString(verificationResponse));
            String clientName = processClientName(verificationProcessDetailedResponse.getData());
            result.setClientName(clientName);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error parsing verification process detailed response object in to a string. " +
                    "Actual object is {} ", verificationProcessDetailedResponse, e);
        } catch (RequestTransformerException e) {
            LOGGER.error("Unable to transform internal state object {} to a verification response",
                    verificationProcessDetailedResponse, e);
        }
        result.setFinalProcessingStatus(verificationResponse.getStatus());
        return result;
    }
   
    private String processClientName(List<FieldData> input) {
        String clientName = "";
        try {
            DataMapper dataMapper = Mappers.getMapper(DataMapper.class);
            List<Data> dataList = input.stream().map(element -> dataMapper.fieldDataToData(element))
                    .collect(Collectors.toList());
            for (Data data : dataList) {
                if (data.getId().equalsIgnoreCase("surname") || data.getId().equalsIgnoreCase("given_names")) {
                    List<DataValues> dataValuesList = data.getValue();
                    if (dataValuesList.size() > 0) {
                        DataValues dataValues = dataValuesList.get(0);
                        clientName += dataValues.getValue();
                        clientName += " ";
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.error("Error in preparing the client name from the processed documents ", e);
        }
        return clientName.trim();
    }
}
