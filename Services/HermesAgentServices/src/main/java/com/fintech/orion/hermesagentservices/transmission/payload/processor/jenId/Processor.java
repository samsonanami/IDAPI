package com.fintech.orion.hermesagentservices.transmission.payload.processor.jenid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.exceptions.payload.PayloadProcessingException;
import com.fintech.orion.hermesagentservices.transmission.payload.model.jenid.JenID;
import com.fintech.orion.hermesagentservices.transmission.payload.model.jenid.mixin.IgnoreOutput;
import com.fintech.orion.hermesagentservices.transmission.payload.model.jenidresultstring.JenIDResultString;
import com.fintech.orion.hermesagentservices.transmission.payload.processor.ProcessorInterface;
import com.fintech.orion.hermesagentservices.transmission.payload.provider.MapperProviderInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by TharinduMP on 10/26/2016.
 * JenID VerificationProcessor
 * Used to create the payload with necessary details from request and cut out the necessary parts from response
 */
public class Processor implements ProcessorInterface {

    @Autowired
    private MapperProviderInterface mapperProvider;

    /**
     * remove unnecessary model parts when creating the request
     * @param payloadModel
     * @return
     * @throws PayloadProcessingException
     */
    private String preProcess(Object payloadModel) throws PayloadProcessingException {
        if (payloadModel != null && payloadModel instanceof JenID) {
            ObjectMapper mapper = mapperProvider.providerNewMapperInstance();
            mapper.addMixIn(JenID.class, IgnoreOutput.class);
            try {
                return mapper.writeValueAsString(payloadModel);
            } catch (JsonProcessingException e) {
                throw new PayloadProcessingException(e);
            }
        } else {
            throw new PayloadProcessingException("payload model provided was null or not an instance of JenID");
        }
    }


    @Override
    public String marshall(Object payloadModel) throws PayloadProcessingException {
        return preProcess(payloadModel);
    }

    @Override
    public String unmarshall(String stringPayload) throws IOException {
        ObjectMapper mapper = mapperProvider.providerNewMapperInstance();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JenID jenID = mapper.readValue(stringPayload, JenID.class);

        JenIDResultString jenIDResultString = mapper.readValue(jenID.getOutputData().getResultString(),JenIDResultString.class);

        return mapper.writeValueAsString(jenIDResultString.getDocumentresult().getBody().getData());
    }
}
