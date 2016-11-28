/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.servlet;

import com.esd.cw.model.User;
import com.esd.cw.services.AdminManageUserService;
import com.esd.cw.services.ReportService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alexgray
 */
public class AdminReportServlet extends HttpServlet {

    ReportService reportService = new ReportService();
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get the logged in user for authentication (the admin)
        User user = (User) request.getSession().getAttribute("user");
        HttpSession session = request.getSession();

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else if (user.isIsAdmin()) {
            try {
                double totalClaimsInPastYear = reportService.getTotalOfAllClaimsInPastYear();
                session.setAttribute("totalClaimsInPastYear", totalClaimsInPastYear);
            } catch (SQLException ex) {
                Logger.getLogger(AdminReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin_report.jsp");
            dispatcher.forward(request, response);
        } else {
            request.getRequestDispatcher("authentication_error.jsp").forward(request, response);
        }
    }
}
