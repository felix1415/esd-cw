package com.esd.cw.model;

import java.util.Date;

public class Payment {

    private final String memberId;
    private final String typeOfPayment;
    private final Date dateOfPayment;
    private final float paymentAmount;

    public Payment(String memberId, String typeOfPayment, float paymentAmount, Date date) {

        this.memberId = memberId;
        this.typeOfPayment = typeOfPayment;
        this.dateOfPayment = date;
        this.paymentAmount = paymentAmount;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public String getTypeOfPayment() {
        return typeOfPayment;
    }

    public String getMemberId() {
        return memberId;
    }

}
