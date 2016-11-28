package com.esd.cw.servlet;

import com.esd.cw.services.ClaimService;
import java.io.IOException;
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
public class PendingClaimsServlet extends HttpServlet {

    ClaimService claimService = new ClaimService();

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * Gets all the pending claims and puts them into the session
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = "pending_claims.jsp";
        HttpSession session = request.getSession();
        request.setAttribute("pendingClaims", claimService.getAllPendingClaimsAndCorrespondingUserModels());
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * Posts the claimId response to a claim, the claim is updated with the
     * claim action from the form
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String claimId = request.getParameter("claimId");
        boolean accept = false;
        if (request.getParameter("action").equals("accept")) {
            accept = true;
        }
        claimService.updateClaim(accept, claimId);
        doGet(request, response);
    }
}
