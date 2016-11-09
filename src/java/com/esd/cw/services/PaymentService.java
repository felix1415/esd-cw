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
import com.esd.cw.model.User;
import java.util.Date;

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
    public boolean makeMembershipPayment(float value, String typeOfPayment, User user) {
        try {

            userDao = new UserDao();
            memberDao = new MemberDao();
            Date date = new Date();
            String memId = user.getUserId();
            Member member = memberDao.findById(memId);

            //Process the payment with given values plus curent date
            paymentDao.makePayment(value, typeOfPayment, memId, date);

            //Update member and user status....
            member.setStatus("PENDING");
            user.setUserStatus("PENDING");

            //update the database to reflect the changes
            userDao.updateUserStatus(user);
            memberDao.updateMemberStatus(member);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
