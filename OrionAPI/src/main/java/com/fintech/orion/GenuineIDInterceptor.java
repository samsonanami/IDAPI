package com.fintech.orion;

import com.fintech.orion.model.ResponseMessage;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GenuineIDInterceptor implements HandlerInterceptor {

    private static final String TAG = "GenuineIDInterceptor: ";

    private static final Logger LOGGER = LoggerFactory.getLogger(GenuineIDInterceptor.class);

    @Autowired
    private String contentType;

    @Autowired
    private String applicationJson;

    @Autowired
    private String attemptedToLogWithoutAuthentication;

    @Autowired
    private String authenticationHeaderIsNotPresentOrInvalid;

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
            response.addHeader(contentType, applicationJson);
            Gson gson = new Gson();
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setStatus(HttpServletResponse.SC_FORBIDDEN);
            if (securityHeader == null || !securityHeader.equalsIgnoreCase(developerKey)) {
                
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(attemptedToLogWithoutAuthentication);
                }
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);

                responseMessage.setMessage(authenticationHeaderIsNotPresentOrInvalid);
                String errorMessageToBeReturned = gson.toJson(responseMessage);
                response.getWriter().write(errorMessageToBeReturned);
                return false;
            }
        } catch (IOException e) {
            LOGGER.error(TAG, e);
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
        LOGGER.info(o.toString());
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
        LOGGER.info(o.toString());
    }
}
