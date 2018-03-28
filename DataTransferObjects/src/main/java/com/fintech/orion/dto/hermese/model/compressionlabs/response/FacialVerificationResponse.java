package com.fintech.orion.dto.hermese.model.compressionlabs.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sasitha on 2/18/17.
 *
 */
public class FacialVerificationResponse {

    @JsonProperty("face_match")
    private String face_match;

    @JsonProperty("liveness")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String liveness;

    @JsonProperty("commands_followed")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String commands_followed;

    @JsonProperty("error")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;




    public String getLiveness() {
        return liveness;
    }

    public void setLiveness(String liveness) {
        this.liveness = liveness;
    }

    public String getCommands_followed() {
        return commands_followed;
    }

    public void setCommands_followed(String commands_followed) {
        this.commands_followed = commands_followed;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


//

    public String getFace_match() {
        return face_match;
    }

    public void setFace_match(String face_match) {
        this.face_match = face_match;
    }


    @Override
    public String toString() {
        return "FacialVerificationResponse{" +
                "liveness=" + liveness +
                "commands_followed=" + commands_followed +
                "face_match=" + face_match +
                "error=" + error +
                '}';
    }
}
