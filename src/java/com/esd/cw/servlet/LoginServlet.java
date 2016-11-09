/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.servlet;

import com.esd.cw.model.User;
import com.esd.cw.services.LoginService;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author alexgray
 */
public class LoginServlet extends HttpServlet
{
  /** Handles the HTTP <code>GET</code> method.
   * @param request servlet request
   * @param response servlet response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
   */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String path = "login.jsp";
        HttpSession session = request.getSession();
        if(session.getAttribute("username") != null )
        {
            path = "dashboard.jsp";
        }        
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
        
    }
  
  /** Handles the HTTP <code>POST</code> method.
   * @param request servlet request
   * @param response servlet response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
   */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
    
        //get username and 
        String username = request.getParameter("username");
        String password = request.getParameter("password");
    
        LoginService loginService = new LoginService();
        User user = loginService.login(username, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        
        if(user != null)
        {
            String path;
            if("PAID".equals(user.getUserStatus()))
            {
                path = "paid_member_dashboard.jsp";
            }
            else
            {
                path = "unpaid_member_dashboard.jsp";
            }
//            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            request.getRequestDispatcher(path).forward(request, response);
        }
        else
        {
            String loginMessage = "Incorrect login information!";
            request.setAttribute("loginMessage", loginMessage);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    
        
    }    
}
