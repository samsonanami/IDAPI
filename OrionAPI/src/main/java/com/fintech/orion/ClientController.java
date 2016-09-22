package com.fintech.orion;

import com.fintech.orion.coreservices.ClientServiceInterface;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ClientController {

    @Autowired
    private ClientServiceInterface clientServiceInterface;

    @RequestMapping(value = "v1/clients", method = RequestMethod.GET)
    @ResponseBody
    public Object getDashboardData(HttpSession session, HttpServletResponse response) throws HibernateException {
        try {
            return clientServiceInterface.getClientList();
        } catch (Exception ex) {
            return null;
        }
    }

}
