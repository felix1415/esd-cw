/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.servlet;

import com.esd.cw.model.User;
import com.esd.cw.services.AdminDashboardService;
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
public class AdminDashboardServlet extends HttpServlet {

    AdminDashboardService ads = new AdminDashboardService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            try {
                // get all users data
                request.setAttribute("allUsers", ads.getUsers());
            } catch (SQLException ex) {
                Logger.getLogger(AdminDashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        }
    }
}
