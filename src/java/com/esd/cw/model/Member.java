/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.model;

import java.util.Date;

/**
 *
 * @author alexgray
 */
public class Member
{
    
    private final String memberId;
    private String name;
    private String address;
    private final Date dateOfBirth;
    private final Date dateOfRegistration;
    private String status;
    private float balance;
    private int claimsRemaining;

    public Member()
    {
        this.memberId = null;
        this.name = null;
        this.address = null;
        this.dateOfBirth = null;
        this.dateOfRegistration = null;
        this.status = null;
        this.balance = 0;
        this.claimsRemaining = 0;
    }

    public int getClaimsRemaining()
    {
        return claimsRemaining;
    }

    public void setClaimsRemaining(int claimsRemaining)
    {
        this.claimsRemaining = claimsRemaining;
    }

    public Member(String memberId, String name, String address, Date dateOfBirth, Date dateOfRegistration, String status, float balance, int claimsRemaining)
    {
        this.memberId = memberId;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.dateOfRegistration = dateOfRegistration;
        this.status = status;
        this.balance = balance;
        this.claimsRemaining = claimsRemaining;
    }

    public Member(String memberId, String name, Date dateOfBirth)
    {
        this.memberId = memberId;
        this.name = name;
        this.address = "";
        this.dateOfBirth = dateOfBirth;
        this.dateOfRegistration = new Date();
        this.status = "Unpaid";
        this.balance = (float)0.0;
        this.claimsRemaining = 0;
    }
    
    public float getBalance()
    {
        return balance;
    }

    public void setBalance(float balance)
    {
        this.balance = balance;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Date getDateOfRegistration()
    {
        return dateOfRegistration;
    }

    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMemberId()
    {
        return memberId;
    }

}
