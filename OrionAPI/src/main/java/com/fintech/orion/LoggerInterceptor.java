package com.fintech.orion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("Before handling the request");
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.info("After handling the request");
        response.addHeader("Access-Control-Allow-Origin", "*");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.info("After rendering the view");
        super.afterCompletion(request, response, handler, ex);
    }
}
