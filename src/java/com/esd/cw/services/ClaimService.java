/*
 * Stephen Turner, Computer Science BSc Year 3
 * University Of the West Of England
 */
package com.esd.cw.services;

import com.esd.cw.dao.ClaimDao;
import com.esd.cw.dao.MemberDao;
import com.esd.cw.model.Claim;
import com.esd.cw.model.Member;
import com.esd.cw.model.User;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sturner
 */
public class ClaimService {

    MemberDao memberDao;
    ClaimDao claimDao;
    MemberService memberService;
    long sixMonthsAgo;
    long membersLastPayment;

    public ClaimService() {
        this.memberDao = new MemberDao();
        this.claimDao = new ClaimDao();
        this.memberService = new MemberService();

    }
    /**
     * Performs checks on users status and date of their first payment.
     * Returns Hash map containing boolean result of the checks and a corresponding message.
     * @param user
     * @param claimAmount
     * @return
     * @throws SQLException
     * @throws ParseException 
     */
    public Map<String, String> validateClaim(User user, double claimAmount) throws SQLException, ParseException {

        MemberService memberService = new MemberService();
        Map<String, String> claimResponse = new HashMap();
        //Retrieve value of date 6 months ago
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -6);
        long sixMonthsAgo = cal.getTime().getTime();
        long membersFirstPaymnet = memberDao.getFirstMembership(user.getUserId());
        
        if (!"PAID".equals(user.getStatus())) {
            claimResponse.put("success", "false");
            claimResponse.put("message", "You're not a paid member");

            return claimResponse;
        }
        if (!(membersFirstPaymnet < sixMonthsAgo) && membersFirstPaymnet != 0) {

            claimResponse.put("success", "false");
            claimResponse.put("message", "You've not waited the arbitrary time limit of 6 months");
            return claimResponse;

        }

        claimResponse.put("success", "true");
        claimResponse.put("message", "You're claim is pending approval");

        return claimResponse;

    }

    public List<Claim> getAllPendingClaims() {
        List<Claim> pendingClaims = new ArrayList<>();
        try {
            pendingClaims = claimDao.getAllPendingClaims();
        } catch (SQLException ex) {
            System.out.println("Failed to get all pending claims : " + ex);
        }
        return pendingClaims;
    }

    public void makeClaim(double claimAmount, String rationale, String memId) throws SQLException {
        Claim claim = new Claim((float) claimAmount, rationale, memId, new Date(), "PENDING");
        claimDao.makeClaim(claim);
    }

    public boolean chargeAllUsersForClaims() {
        boolean success = false;
        try {
            int totalUsers = memberDao.findAll().size();
            String totalOfAllClaimsString = claimDao.getTotalOfAllClaims();
            Double amountToDeduct = 0.0;
            if (!(totalOfAllClaimsString.equals("null"))) {
                amountToDeduct = Double.valueOf(claimDao.getTotalOfAllClaims()) / totalUsers;
                memberDao.deductAmountFromAllUsers(amountToDeduct);
            }
            success = true;
        } catch (Exception e) {
            System.out.println("Failed to charge all users based on total claims");
            e.printStackTrace();
        }
        return success;
    }

    public String claimStatus(User user) throws SQLException, ParseException {

        MemberService memberService = new MemberService();
        Map<String, String> claimResponse = new HashMap();
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -6);
        long sixMonthsAgo = cal.getTime().getTime();
        long membersFirstPayment = memberDao.getFirstMembership(user.getUserId());
        Member member = new Member();
        member = memberDao.findById(user.getUserId());

        if (membersFirstPayment < sixMonthsAgo && membersFirstPayment != 0) {

            return "You are able to claim";

        } else {

            return "You are unable to claim";

        }
    }

    public boolean updateClaim(boolean accept, String claimId) {
        return claimDao.updateClaim(accept, claimId);
    }
}
