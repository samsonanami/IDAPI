package com.fintech.orion.rest;

import com.fintech.orion.dto.response.api.ResourceUploadResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-18T09:12:11.427Z")

@Api(value = "content", description = "the content API")
public interface ContentApi {

    @ApiOperation(value = "Submit new resources to process", notes = "Submit your passport image, driving license image or any other allowed content to process", response = ResourceUploadResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ResourceUploadResponse.class),
        @ApiResponse(code = 400, message = "Bad request", response = ResourceUploadResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized request", response = ResourceUploadResponse.class),
        @ApiResponse(code = 413, message = "Request entity too large", response = ResourceUploadResponse.class),
        @ApiResponse(code = 415, message = "Unsupported media type", response = ResourceUploadResponse.class) })
    @RequestMapping(value = "/v1/content/{contentType}",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Object> contentContentTypePost(
            @ApiParam(value = "type of the content you are submiting \"image\" \"video\" \"file\"", required = true)
            @PathVariable("contentType") String contentType,
            @ApiParam(value = "file detail") @RequestPart("file") MultipartFile file,
            HttpServletResponse response, HttpServletRequest request);

}
