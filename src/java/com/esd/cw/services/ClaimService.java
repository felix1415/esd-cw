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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

    public Map<String, String> validateClaim(User user, double claimAmount) throws SQLException, ParseException {

        MemberService memberService = new MemberService();
        Map<String, String> claimResponse = new HashMap();
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -6);
        long sixMonthsAgo = cal.getTime().getTime();
        long membersLastPayment = claimDao.getRegistrationDate(user.getUserId());
        Member member = memberDao.findById(user.getUserId());
        
        if (!"PAID".equals(user.getStatus())) {
            claimResponse.put("success", "false");
            claimResponse.put("message", "You're not a paid member");

            return claimResponse;
        }

        if (!memberService.claimBalanceCheck(user.getUserId(), claimAmount)) {
            claimResponse.put("success", "false");
            claimResponse.put("message", "You're balance is insufficuent to cover the excess on this claim");

            return claimResponse;

        } else if (!(membersLastPayment < sixMonthsAgo)) {

            claimResponse.put("success", "false");
            claimResponse.put("message", "You've not waited the arbitrary time limit of 6 moths");
            return claimResponse;

        } else if (member.getClaimsRemaining() < 1) {
            claimResponse.put("success", "false");
            claimResponse.put("message", "You've used all your claims for this year");
            return claimResponse;

        }

        claimResponse.put("success", "true");
        claimResponse.put("message", "You're claim is pending approval");

        return claimResponse;

    }

  public void makeClaim(double claimAmount, String rationale, String memId) throws SQLException {
        Claim claim = new Claim((float) claimAmount, rationale, memId, new Date(), "PENDING");

        claimDao.makeClaim(claim);

    }

  
   public String claimStatus (User user) throws SQLException, ParseException {
   
   MemberService memberService = new MemberService();
   Map<String, String> claimResponse = new HashMap();
   Calendar cal = Calendar.getInstance();
   cal.add(cal.MONTH, -6);
   long sixMonthsAgo = cal.getTime().getTime();
   long membersLastPayment = claimDao.getMembershipDate(user.getUserId());
   Member member = new Member();
   member = memberDao.findById(user.getUserId());
   
   
   if (membersLastPayment < sixMonthsAgo && member.getClaimsRemaining() > 0) {
   
   return "Ineligible to claim";
       
    } else {
     
  return "Eligble to claim";
       
    }
}
}
