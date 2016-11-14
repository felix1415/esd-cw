/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.servlet;

import com.esd.cw.dao.MemberDao;
import com.esd.cw.model.Member;
import com.esd.cw.model.User;
import com.esd.cw.services.DashboardService;
import com.esd.cw.services.LoginService;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author alexgray
 */
public class UnPaidMemberServlet extends HttpServlet
{
    MemberDao memberDao = new MemberDao();
    /** Handles the HTTP <code>GET</code> method.
   * @param request servlet request
   * @param response servlet response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
   */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String path;
        HttpSession session = request.getSession();
        DashboardService dbService = new DashboardService();
        
        //get user from session, check if logged in
        User user = (User) session.getAttribute("user");
        if(user != null )
        {
            //go to the paid member dashboard if member has paid
            if(user.getMember().getStatus().equals("PAID"))
            {
                path = "paid_member_dashboard.jsp";
            }
            else
            {
                path = "unpaid_member_dashboard.jsp";
            }
        } else {
            path = "index.jsp";
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
        
    }
  
  /** Handles the HTTP <code>POST</code> method.
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