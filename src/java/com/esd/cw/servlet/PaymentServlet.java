/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.servlet;

import com.esd.cw.model.Member;
import com.esd.cw.services.MemberService;
import com.esd.cw.services.PaymentService;
import com.esd.cw.services.UserService;
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
 * @author shaun
 */
public class PaymentServlet extends HttpServlet {

    UserService userService;
    MemberService memberService;
    PaymentService paymentService;

    public PaymentServlet() {
        this.paymentService = new PaymentService();
        this.memberService = new MemberService();
        this.userService = new UserService();
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
     * Create a new payment for membership. 
     * Takes the inputs from the form in order to create a payment in the database.
     * Form returns an amount(double) and the type of payment(string).
     *
     *Session is then updated to match members new status.
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

        try {
            String paymentResponse = paymentService.makeMembershipPayment(paymentAmount, paymentType, user);

            //update session user and member
            user = userService.getUser(user.getUserId());
            session.setAttribute("user", user);
            Member member = memberService.getMember(user.getUserId());
            session.setAttribute("member", member);

            request.setAttribute("paymentStatus", paymentResponse);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("payment.jsp");

        dispatcher.forward(request, response);
    }
}
