package com.fintech.orion.rest;

import com.fintech.orion.dto.mrz.ScriptResult;
import com.fintech.orion.dto.request.api.MRZRequest;
import com.fintech.orion.dto.request.api.VerificationRequest;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.dto.response.api.VerificationRequestResponse;
import com.fintech.orion.dto.response.api.VerificationRequestSummery;
import com.fintech.orion.dto.response.external.VerificationResponse;

import io.swagger.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


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


    @CrossOrigin
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
            @RequestParam(value = "status", required = false) List<String> status,
            @RequestParam(value = "clientName", required = false) String clientName, HttpServletRequest request,
            HttpServletResponse response);

    /*
     * Update verification details API
     */
    @ApiOperation(value = "Update verification data", notes = "Updating existing verification data", response = VerificationRequestResponse.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = VerificationRequestResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = VerificationRequestResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized request", response = VerificationRequestResponse.class) })
    @CrossOrigin
    @RequestMapping(value = "/v1/verification/{verificationId}", produces = { "application/json" }, consumes = {
            "application/json" }, method = RequestMethod.PUT)
    ResponseEntity<Object> updateVerificationData(
            @ApiParam(value = "verification id", required = true) @PathVariable("verificationId") String resourceId,
            @ApiParam(value = "Processing request", required = true) @RequestBody VerificationResponse body,
            HttpServletResponse response, HttpServletRequest request);

    /*
     * Update re-verification details API
     */
    @ApiOperation(value = "Update re-verification data", notes = "Updating re-verification data", response = VerificationRequestResponse.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = VerificationRequestResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = VerificationRequestResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized request", response = VerificationRequestResponse.class) })
    @CrossOrigin
    @RequestMapping(value = "/v1/verification/{verificationId}/reverify", produces = {
            "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
    ResponseEntity<Object> updateReVerificationData(
            @ApiParam(value = "verification id", required = true) @PathVariable("verificationId") String resourceId,
            @ApiParam(value = "Processing request", required = true) @RequestBody VerificationResponse body,
            HttpServletResponse response, HttpServletRequest request);

    /*
     * Generate MRZ code
     */
    @ApiOperation(value = "Submit your passport details request", notes = "Generating MRZ code", response = ScriptResult.class, tags = {})
    @ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = ScriptResult.class),
            @ApiResponse(code = 400, message = "Bad request", response = ScriptResult.class),
            @ApiResponse(code = 401, message = "Unauthorized request", response = ScriptResult.class) })
    @CrossOrigin
    @RequestMapping(value = "/v1/verification/generatemrz", produces = { "application/json" }, consumes = {
            "application/json" }, method = RequestMethod.POST)
    ResponseEntity<Object> generateMRZCode(
            @ApiParam(value = "Processing request", required = true) @RequestBody MRZRequest mrzRequestBody,
            HttpServletResponse response, HttpServletRequest request);

}
