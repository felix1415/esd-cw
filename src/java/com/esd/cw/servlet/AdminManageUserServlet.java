/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.servlet;

import com.esd.cw.model.User;
import com.esd.cw.services.AdminManageUserService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aidanayala-angear
 */
public class AdminManageUserServlet extends HttpServlet {

    AdminManageUserService manageUserService = new AdminManageUserService();

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

        // get the logged in user for authentication
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else if (user.isIsAdmin()) {
            try {
                request.setAttribute("manageUser", manageUserService.getUser(request.getParameter("userId")));
            } catch (SQLException ex) {
                Logger.getLogger(AdminManageUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("manage_user.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("authentication_error.jsp").forward(request, response);
        }
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

        // get the logged in user for authentication (the admin)
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else if (user.isIsAdmin()) {
            try {
                manageUserService.updateUserStatus(request.getParameter("userId"), request.getParameter("newStatus"));
            } catch (SQLException ex) {
                Logger.getLogger(AdminManageUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect(request.getContextPath() + "/admin/manageuser?userId=" + request.getParameter("userId"));
        } else {
            request.getRequestDispatcher("authentication_error.jsp").forward(request, response);
        }
    }
}
