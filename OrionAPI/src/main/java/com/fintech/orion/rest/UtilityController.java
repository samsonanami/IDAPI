package com.fintech.orion.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Created by sasitha on 11/29/16.
 */
@Controller
public class UtilityController {

    @RequestMapping(value = "/v1/health", method = RequestMethod.GET)
    @ResponseBody
    public Object getV1health(HttpServletRequest request, HttpServletResponse response){
        return "V1 health status ok";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Object discover(HttpServletRequest request, HttpServletResponse response){
        return "Api discovery temporarily un available";
    }
}
