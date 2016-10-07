package com.fintech.orion.messaging.map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

/**
 * Created by TharinduMP on 10/7/2016.
 * Provide a know implementation to transfer job data between consumer and producer
 */
public class Map {

    private Map() {
    }

    /**
     * The Map Utility is created for send job data on a specified format
     * @param jobData
     * @return
     * @throws JsonProcessingException
     */
    public static String objectToStringMapping(java.util.Map<String, Object> jobData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(jobData);
    }

    /**
     * The Map Utility is created for receive job data on a specified format
     * @param jobData
     * @return
     * @throws JsonProcessingException
     */
    public static HashMap<String, Object> objectToMapping(Object jobData) {
        return (HashMap<String, Object>) jobData;
    }
}
