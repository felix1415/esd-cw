/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.servlet;

import com.esd.cw.*;
import com.esd.cw.dao.MemberDao;
import com.esd.cw.model.Member;
import com.esd.cw.model.User;
import com.esd.cw.services.PaymentService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shaun
 */
public class PaymentServlet extends HttpServlet {
    
    PaymentService paymentService;
    MemberDao memberDao = new MemberDao();
    
    public PaymentServlet() {
        this.paymentService = new PaymentService();
    }

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
        
        request.getRequestDispatcher("payment.jsp").forward(request, response);
        
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
        
        String paymentType = request.getParameter("paymentType");
        System.out.println(paymentType);
        int paymentAmount = Integer.parseInt(request.getParameter("paymentAmount"));
        System.out.println(paymentAmount);
        
        HttpSession session = request.getSession();
        com.esd.cw.model.User user = (com.esd.cw.model.User) session.getAttribute("user");
        
        Member member = memberDao.findById(user.getUserId());
        member.setBalance(member.getBalance() + paymentAmount);
        
        paymentService.makeMembershipPayment(paymentAmount, paymentType, user, member);
        
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
