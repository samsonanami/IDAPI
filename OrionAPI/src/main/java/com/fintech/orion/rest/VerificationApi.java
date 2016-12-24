package com.fintech.orion.rest;

import com.fintech.orion.dto.request.api.VerificationRequest;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.dto.response.api.VerificationRequestResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-18T09:12:11.427Z")

@Api(value = "verification", description = "the verification API")
public interface VerificationApi {

    @ApiOperation(value = "Submit your processing request", notes = "New processing request", response = VerificationRequestResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = VerificationRequestResponse.class),
        @ApiResponse(code = 400, message = "Bad request", response = VerificationRequestResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized request", response = VerificationRequestResponse.class) })
    @RequestMapping(value = "/v1/verification",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Object> verificationPost(
            @ApiParam(value = "", required = true, defaultValue = "true")
            @RequestParam(value = "integrationRequest", required = true, defaultValue = "true") String integrationRequest,
            @ApiParam(value = "Processing request", required = true) @RequestBody VerificationRequest body,
            HttpServletResponse response, HttpServletRequest request);


    @ApiOperation(value = "Get details", notes = "get details", response = VerificationProcessDetailedResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "success", response = VerificationProcessDetailedResponse.class),
        @ApiResponse(code = 400, message = "Bad request", response = VerificationProcessDetailedResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized request", response = VerificationProcessDetailedResponse.class),
        @ApiResponse(code = 404, message = "Requested procesisng request is not found", response = VerificationProcessDetailedResponse.class) })
    @RequestMapping(value = "/v1/verification/{verificationId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Object> verificationVerificationIdGet(@ApiParam(value = "verification id", required = true)
                                                         @PathVariable("verificationId") String verificationId,
                                                         HttpServletResponse response, HttpServletRequest request);

}
