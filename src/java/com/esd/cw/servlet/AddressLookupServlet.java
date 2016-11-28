package com.esd.cw.servlet;

import com.esd.cw.services.AddressLookupService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddressLookupServlet extends HttpServlet {

    AddressLookupService addressLookupService = new AddressLookupService();

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * Gets an address from the AddressLookupService with a postcode
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String postcode = request.getParameter("postcode");
        response.setContentType("application/json");
        String failureJsonString = "{\"status\":\"failure\"}";
        try {
            String responseJson = addressLookupService.doLookup(postcode);
            response.getWriter().println(responseJson);
        } catch (Exception ex) {
            response.getWriter().println(failureJsonString);
            System.out.println("Failed to get address lookup for postcode " + postcode);
            ex.printStackTrace();
        }
    }
}
