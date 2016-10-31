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
public class Claim
{
    
    private final int claimId;
    private final int memberId;
    private Date claimDate;
    private String rationale;
    private String status;
    private float amount;

    public Claim(int claimId, int memberId)
    {
        this.claimId = claimId;
        this.memberId = memberId;
        this.claimDate = new Date();
        this.rationale = "";
        this.status = "";
        this.amount = (float)0.0;
    }

    public float getAmount()
    {
        return amount;
    }

    public void setAmount(float amount)
    {
        this.amount = amount;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getRationale()
    {
        return rationale;
    }

    public void setRationale(String rationale)
    {
        this.rationale = rationale;
    }

    public Date getClaimDate()
    {
        return claimDate;
    }

    public void setClaimDate(Date claimDate)
    {
        this.claimDate = claimDate;
    }

    public int getMemberId()
    {
        return memberId;
    }

    public int getClaimId()
    {
        return claimId;
    }

}
