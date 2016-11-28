/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.servlet;

import com.esd.cw.model.User;
import com.esd.cw.services.UserService;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author alexgray
 */
public class LoginServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * Checks if a user is logged in, dispatches to dashboard if already logged
     * in
     *
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
        if (session.getAttribute("username") != null) {
            path = "dashboard.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * Uses UserService to log a user in with a username and password, puts user
     * in the session and redirects to the correct dashboard
     *
     * @param request servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        //get username and password
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //get user from login service, add user to session
        UserService loginService = new UserService();
        User user = null;
        try {
            user = loginService.login(username, password);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        //send to paid/unpaid dashboard dependent on user status
        if (user != null) {
            String path;
            if ("PAID".equals(user.getUserStatus())) {
                path = "pmember";
            } else if (user.isIsAdmin()) {
                path = "admin/dashboard";
            } else {
                path = "upmember";
            }
            response.sendRedirect(path);
        } else {
            String loginMessage = "Incorrect login information!";
            request.setAttribute("loginMessage", loginMessage);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
