package com.fintech.orion.hermesagentservices.processor.request.processor;

import java.util.Map;

public class Support {

    public boolean set_CommandFollowed = false;
    public String f1Type = "";


    public void set(Map<String, Object>requestBodyContent){

        for ( String objectKey : requestBodyContent.keySet()){


            String key =objectKey.toString();
            String value = requestBodyContent.get(objectKey).toString();
            System.out.println(key + " *****************" + value);

            if(key.equalsIgnoreCase("f3")){
                set_CommandFollowed = true;
            }

            if(key.equalsIgnoreCase("f1")){
                String string = value;
                String[] parts = string.split("\\.");
                String part2 = parts[1];

                if (part2.equalsIgnoreCase("mp4")){
                    f1Type = "video/mp4";
                }
                else {
                    f1Type = "image/jpeg";
                }

            }

        }

    }

}
