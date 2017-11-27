package com.fintech.orion.dto.mrz;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/*
 * ScriptResultReader
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-11-09T10:12:11.427Z")
public class ScriptResultReader {
    private ObjectMapper objectMapper;

    public ScriptResultReader(){
        objectMapper = new ObjectMapper();
    }

    public ScriptResult read(String scriptResult) throws IOException {
        return objectMapper.readValue(scriptResult, ScriptResult.class);
    }
}
