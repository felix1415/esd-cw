/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author aidanayala-angear
 */
public class LogoutServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * Destroys a session and redirects to the login page
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // get session
        HttpSession session = request.getSession();
        
        // destroy it
        session.invalidate();
        
        // redirect to login page
        response.sendRedirect("login");
    }
}
