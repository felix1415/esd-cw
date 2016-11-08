/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.services;

import com.esd.cw.dao.MemberDao;
import com.esd.cw.dao.PaymentDao;
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

    public PaymentService() {
        paymentDao = new PaymentDao();
        memberDao = new MemberDao();
    }

    public boolean makeMembershipPayment(float value, String typeOfPayment, User user, Member member) {
        try {

            Date date = new Date();
            String memId = user.getUserId();

            memberDao.updateMember(member);

            paymentDao.makePayment(value, typeOfPayment, memId, date);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
