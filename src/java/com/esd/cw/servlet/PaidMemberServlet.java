/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.servlet;

import com.esd.cw.dao.ClaimDao;
import com.esd.cw.dao.MemberDao;
import com.esd.cw.dao.PaymentDao;
import com.esd.cw.model.Claim;
import com.esd.cw.model.Payment;
import com.esd.cw.model.User;
import com.esd.cw.services.DashboardService;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author alexgray
 */
public class PaidMemberServlet extends HttpServlet {

    MemberDao memberDao = new MemberDao();
    PaymentDao paymentDao = new PaymentDao();
    ClaimDao claimDao = new ClaimDao();

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path;
            HttpSession session = request.getSession();
            DashboardService dbService = new DashboardService();
            List<Payment> payments;
            List<Claim> claims;
            
            //get user from session, check if logged in
            User user = (User) session.getAttribute("user");
            //Retrieve all payments and claims for users history...
            payments = paymentDao.findPaymentsForUser(user.getUserId());
            claims = claimDao.getClaimsForMember(user.getUserId());
            
            // memberClaims = claimDao.
            // membersPayments = memberDao.
            if (user != null) {
                
                //go to the paid member dashboard if member has paid
                if (user.getMember().getStatus().equals("PAID")) {
                    path = "paid_member_dashboard.jsp";
                } else {
                    path = "unpaid_member_dashboard.jsp";
                }
            } else {
                path = "/";
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PaidMemberServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
