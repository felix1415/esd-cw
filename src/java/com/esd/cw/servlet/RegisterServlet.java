/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.servlet;

import com.esd.cw.services.RegistrationService;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aidanayala-angear
 */
public class RegisterServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // display register page
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // define forwarding path
        String forwardPath = "login.jsp";
        
        // instance registration service
        RegistrationService registerService = new RegistrationService();
        
        // register the user
        Map registerResponse = registerService.registerUser(
                request.getParameter("username"), 
                request.getParameter("firstName"), 
                request.getParameter("lastName"), 
                request.getParameter("address"), 
                request.getParameter("dob"), 
                request.getParameter("password"),
                request.getParameter("confirmPassword")
        );
        
        // if registration was not successfull, change the forwarding path
        if (!Boolean.valueOf((String) registerResponse.get("success"))) {
            forwardPath = "register.jsp";
        }
        
        request.setAttribute("registerResponse", registerResponse);
        request.getRequestDispatcher(forwardPath).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
