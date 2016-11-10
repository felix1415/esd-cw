/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.model;

import com.esd.cw.dao.MemberDao;
import com.esd.cw.dao.PaymentDao;
import java.util.List;

/**
 *
 * @author alexgray
 */
public class User {

    private String userId;
    private String password;
    private String userStatus;
    private boolean isAdmin;
    Member member;
    List<Payment> payments;

    public User() {

    }

    public User(String userId, String password, String userStatus, boolean isAdmin) {
        this.userId = userId;
        this.password = password;
        this.userStatus = userStatus;
        this.isAdmin = isAdmin;
        
        // if they are not an admin. set their member record
        if (!this.isAdmin) {
            setMember();
            setPayments();
        }
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }
    
    // TODO correct way to do this?
    private void setMember() {
        MemberDao memberDao = new MemberDao();
        this.member = memberDao.findById(this.userId);
    }
    
    private void setPayments() {
        PaymentDao paymentDao = new PaymentDao();
        this.payments = paymentDao.findPaymentsForUser(this.userId);
    }
    
    public Member getMember() {
        return member;
    }
    
    public List<Payment> getPayments() {
        return payments;
    }

    public String getStatus() {
        return userStatus;
    }
}
