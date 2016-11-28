package com.esd.cw.model;

import java.util.Date;

public class Claim {

    private int id;
    private final String memberId;
    private Date claimDate;
    private String rationale;
    private String status;
    private float amount;

    public Claim(int id, float amount, String rationale, String memberId, Date claimDate, String status) {
        this.memberId = memberId;
        this.claimDate = claimDate;
        this.rationale = rationale;
        this.status = status;
        this.amount = amount;
        this.id = id;
    }

    public Claim(float amount, String rationale, String memberId, Date claimDate, String status) {
        this.memberId = memberId;
        this.claimDate = claimDate;
        this.rationale = rationale;
        this.status = status;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRationale() {
        return rationale;
    }

    public void setRationale(String rationale) {
        this.rationale = rationale;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public String getMemberId() {
        return memberId;
    }
}
