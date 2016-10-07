package com.fintech.orion;

import com.fintech.orion.model.ResponseMessage;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DeveloperAuthenticationInterceptorV2 implements HandlerInterceptor {


    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE = "content-type";
    private static final String ATTEMPTED_TO_LOG_WITHOUT_AUTHENTICATION = "Attempted to log without authentication";
    private static final String AUTHENTICATION_HEADER_IS_NOT_PRESENT_OR_INVALID = "Authentication Headers not present or is invalid";
    private static final Logger LOGGER = Logger.getLogger(DeveloperAuthenticationInterceptorV2.class);

    /**
     * The developer key used for authentication.
     */
    @Autowired
    private String developerKey;



    /**
     * The Authentication header name.
     */
    @Autowired
    private String securityTokenHeaderName;



    /**
     * The method responsible to handle the authentication tokens.
     *
     * @param request  The reference to the request object.
     * @param response The reference to the response object.
     * @param handler  The reference to the handler object.
     * @return Returns the status whether authentication was successful or not.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String securityHeader;
        try {
            securityHeader = request.getHeader(securityTokenHeaderName);
            response.addHeader(CONTENT_TYPE, APPLICATION_JSON);
            Gson gson = new Gson();
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setStatus(HttpServletResponse.SC_FORBIDDEN);
            if (securityHeader == null || !securityHeader.equalsIgnoreCase(developerKey)) {
                
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(ATTEMPTED_TO_LOG_WITHOUT_AUTHENTICATION);
                }
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);

                responseMessage.setMessage(AUTHENTICATION_HEADER_IS_NOT_PRESENT_OR_INVALID);
                String errorMessageToBeReturned = gson.toJson(responseMessage);
                response.getWriter().write(errorMessageToBeReturned);
                return false;
            }
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }


    /**
     * Unimplemented since its not used at this level
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        LOGGER.info(o);
    }

    /**
     * Unimplemented since its not used at this level
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        LOGGER.info(o);
    }
}
