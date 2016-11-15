/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.services;

import com.esd.cw.dao.MemberDao;
import com.esd.cw.dao.PaymentDao;
import com.esd.cw.dao.UserDao;
import com.esd.cw.model.Member;
import com.esd.cw.model.Payment;
import com.esd.cw.model.User;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author shaun
 */
public class PaymentService {

    PaymentDao paymentDao;
    MemberDao memberDao;
    UserDao userDao;

    public PaymentService() {
        paymentDao = new PaymentDao();
        memberDao = new MemberDao();
    }

    /**
     *
     * @param value
     * @param typeOfPayment
     * @param user
     * @return
     */
    public String makeMembershipPayment(float value, String typeOfPayment, User user) throws SQLException {

        userDao = new UserDao();
        memberDao = new MemberDao();
        Date date = new Date();
        String memId = user.getUserId();
        Member member = memberDao.findById(memId);
        String paymentStatus;

        if (user.getStatus().equals("PENDING")) {

            paymentStatus = "Payment failed: Your membership is already being processed";

            return paymentStatus;

        } else if (user.getStatus().equals("PAID")) {

            paymentStatus = "Payment failed: You're already a member";
            return paymentStatus;

        } else if (value < 500) {
            
            paymentStatus = "Payment failed: memberships costs are £500";
            return paymentStatus;
        }

        paymentStatus = "Your payment is pending approval";
        Payment payment = new Payment(memId, typeOfPayment, value);

        paymentDao.makePayment(payment);
        member.setStatus("PENDING");
        user.setUserStatus("PENDING");

        userDao.updateUserStatus(user);
        memberDao.updateMemberStatus(member);
        return paymentStatus;
    }

}
