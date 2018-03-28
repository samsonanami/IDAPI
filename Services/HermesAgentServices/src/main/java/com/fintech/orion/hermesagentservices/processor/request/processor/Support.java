package com.fintech.orion.hermesagentservices.processor.request.processor;

import java.util.Map;

public class Support {

    public boolean set_CommandFollowed = false;
    public String f1Type = "";


    public void set(Map<String, Object>requestBodyContent){

        for ( String objectKey : requestBodyContent.keySet()){


            String key =objectKey.toString();
            String value = requestBodyContent.get(objectKey).toString();
            System.out.println(key + " *********IN Support********" + value);

            if(key.equalsIgnoreCase("commandFollowed")){
                set_CommandFollowed = true;

            }else if(key.equalsIgnoreCase("selfie_video")){
//                String string = value;
//                String[] parts = string.split("\\.");
//                String part2 = parts[1];

//                if (part2.equalsIgnoreCase("mp4")){
//                    f1Type = "video/mp4";
//                }
//                else {
//                    f1Type = "image/jpeg";
//                }
                f1Type = "video/mp4";

            }
            else if (key.equalsIgnoreCase("selfie_image")){
                f1Type = "image/jpeg";

            }

        }

    }

}
