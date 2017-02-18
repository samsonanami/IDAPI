package com.fintech.orion.hermesagentservices.transmission.request.body.builder;

import java.util.Map;

/**
 * Created by sasitha on 2/18/17.
 *
 */
public class CompressionLabsRequestBodyBuilder implements RequestBodyBuilder {
    private static final String LINE_SEPARATOR = "\r\n";
    @Override
    public String getRequestBody(Map<String, Object> bodyContent) {
        String body = "------WebKitFormBoundary7MA4YWxkTrZu0gW";

        for (Map.Entry<String, Object> entry : bodyContent.entrySet()) {
            if (!entry.getKey().contains("-contentType")){
                body = body + LINE_SEPARATOR +
                        "Content-Disposition: form-data; name=\""+entry.getKey()+"\"; " +
                        "filename=\""+ (String)entry.getValue() +"\""+LINE_SEPARATOR+"Content-Type: "
                        +getContentType(bodyContent, entry.getKey())
                        +LINE_SEPARATOR+LINE_SEPARATOR+LINE_SEPARATOR+"------WebKitFormBoundary7MA4YWxkTrZu0gW";
            }
        }
        body = body + "--";
        return body;
    }

    private String getContentType(Map<String, Object> bodyContent, String keyName){
        return (String)bodyContent.get(keyName+"-contentType");
    }
}

