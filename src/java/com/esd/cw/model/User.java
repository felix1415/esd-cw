package com.esd.cw.model;

import java.util.List;

public class User {

    private String userId;
    private String password;
    private String userStatus;
    private boolean isAdmin;
    Member member;
    List<Payment> payments;
    List<Claim> claims;

    public User() {

    }

    public User(String userId, String password, String userStatus, boolean isAdmin) {
        this.userId = userId;
        this.password = password;
        this.userStatus = userStatus;
        this.isAdmin = isAdmin;
    }

    public User(String userId, String password, String userStatus, boolean isAdmin, Member member, List<Payment> payments, List<Claim> claims) {
        this.userId = userId;
        this.password = password;
        this.userStatus = userStatus;
        this.isAdmin = isAdmin;
        this.member = member;
        this.payments = payments;
        this.claims = claims;
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

    public Member getMember() {
        return member;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public String getStatus() {
        return userStatus;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Claim> getClaims() {
        return claims;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }

}
