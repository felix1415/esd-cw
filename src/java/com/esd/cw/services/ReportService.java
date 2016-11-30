package com.esd.cw.services;

import com.esd.cw.dao.ClaimDao;
import com.esd.cw.dao.MemberDao;
import com.esd.cw.dao.PaymentDao;
import com.esd.cw.dao.UserDao;
import java.sql.SQLException;

public class ReportService
{
    ClaimDao claimDao;
    PaymentDao paymentDao;
    long sixMonthsAgo;
    long membersLastPayment;

    public ReportService() {
        this.claimDao = new ClaimDao();
        this.paymentDao = new PaymentDao();
    }
    
    public double getTotalOfAllClaimsInPastYear() throws SQLException
    {
        try {
            String totalOfAllClaimsString = claimDao.getTotalOfAllClaimsInPastYear();
            if (!(totalOfAllClaimsString.equals("null"))) {
                return Double.valueOf(totalOfAllClaimsString);
            }
        } catch (Exception e) {
            System.out.println("Failed to get total of all claims in the past year");
            e.printStackTrace();
        }
        return -1.0;
    }
    
    public double getTotalOfAllPaymentsInPastYear() throws SQLException
    {
        try {
            String totalOfAllClaimsString = paymentDao.getTotalOfAllPaymentsInPastYear();
            if (!(totalOfAllClaimsString.equals("null"))) {
                return Double.valueOf(totalOfAllClaimsString);
            }
        } catch (Exception e) {
            System.out.println("Failed to get total of all claims in the past year");
            e.printStackTrace();
        }
        return -1.0;
    }
}
