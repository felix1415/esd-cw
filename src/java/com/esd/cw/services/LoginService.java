/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shaun
 */
public class LoginService {
    
    private final HttpServletRequest request;

    public LoginService(HttpServletRequest request)
    {
        this.request = request;
    }
    
    public boolean login(String username, String password)
    {
        if("swag".equals(username) && "yolo".equals(password))
        {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return true;
        }
        return false;
    }    
}
