package com.fintech.orion.rest;

import com.fintech.orion.dto.request.api.VerificationRequest;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.dto.response.api.VerificationRequestResponse;
import com.fintech.orion.dto.response.api.VerificationRequestSummery;
import io.swagger.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-18T09:12:11.427Z")

@Api(value = "verification", description = "the verification API")
public interface VerificationApi {

    @ApiOperation(value = "Submit your processing request", notes = "New processing request", response = VerificationRequestResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = VerificationRequestResponse.class),
        @ApiResponse(code = 400, message = "Bad request", response = VerificationRequestResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized request", response = VerificationRequestResponse.class) })
    @CrossOrigin
    @RequestMapping(value = "/v1/verification",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Object> verificationPost(
            @ApiParam(value = "", required = true, defaultValue = "true")
            @RequestParam(value = "integration_request", required = true, defaultValue = "true") String integrationRequest,
            @ApiParam(value = "Processing request", required = true) @RequestBody VerificationRequest body,
            HttpServletResponse response, HttpServletRequest request);


    @ApiOperation(value = "Get details", notes = "get details", response = VerificationProcessDetailedResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "success", response = VerificationProcessDetailedResponse.class),
        @ApiResponse(code = 400, message = "Bad request", response = VerificationProcessDetailedResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized request", response = VerificationProcessDetailedResponse.class),
        @ApiResponse(code = 404, message = "Requested procesisng request is not found", response = VerificationProcessDetailedResponse.class) })
    @CrossOrigin
    @RequestMapping(value = "/v1/verification/{verificationId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Object> verificationVerificationIdGet(@ApiParam(value = "verification id", required = true)
                                                         @PathVariable("verificationId") String verificationId,
                                                         HttpServletResponse response, HttpServletRequest request);


    @RequestMapping(
            value = "/v1/verification/history",
            params = { "page", "size" },
            produces = { "application/json" },
            method = RequestMethod.GET
    )
    public ResponseEntity<PagedResources<VerificationRequestSummery>> verificationRequestSummeryGet(
            @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "MM-dd-yyyy") Date from,
            @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "MM-dd-yyyy") Date to,
            @RequestParam(value = "page", required = false, defaultValue = "1") String pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") String pageSize,
            HttpServletRequest request, HttpServletResponse response);

}
