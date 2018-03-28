package com.fintech.orion.hermesagentservices.transmission.request.builder;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.BaseRequest;

import java.io.File;
import java.util.Map;

/**
 * Created by sasitha on 2/18/17.
 *
 */
public class CompressionlabsRequestBuilder extends RequestBuilder{

    @Override
    public BaseRequest buildPostRequest(Map<String, String> configurations, Map content) {

        System.out.println(content.get("selfie_image")+"        content.get(selfie_image)");
        System.out.println(content.get("id_image")+"        content.get(id_image)");
        System.out.println(configurations.get("url"));
        if (content.get("commands_file") == null){
            return Unirest.post(configurations.get("url"))
                    .field("selfie_image", new File((String) content.get("selfie_image")))
                    .field("id_image", new File((String) content.get("id_image")));

        }
        else{
            return Unirest.post(configurations.get("url"))
                    .field("selfie_video", new File((String) content.get("selfie_video")))
                    .field("id_image", new File((String) content.get("id_image")))
                    .field("commands_file", new File((String) content.get("commands_file")));

        }


    }
}
