package com.esd.cw.servlet;

import com.esd.cw.model.User;
import com.esd.cw.services.UserService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminDashboardServlet extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String searchTerm = (String) request.getParameter("search");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            try {
                // get all users data
                request.setAttribute("allUsers", userService.getUsersWithIdContaining(searchTerm));
            } catch (SQLException ex) {
                Logger.getLogger(AdminDashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        }
    }
}
