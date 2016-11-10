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
public class Payment
{
    
    private final int paymentId;
    private final String memberId;
    private final String typeOfPayment;
    private final Date dateOfPayment;
    private final float paymentAmount;

    public Payment(int paymentId, String memberId, String typeOfPayment, float paymentAmount)
    {
        this.paymentId = paymentId;
        this.memberId = memberId;
        this.typeOfPayment = typeOfPayment;
        this.dateOfPayment = new Date();
        this.paymentAmount = paymentAmount;
    }

    public float getPaymentAmount()
    {
        return paymentAmount;
    }

    public Date getDateOfPayment()
    {
        return dateOfPayment;
    }

    public String getTypeOfPayment()
    {
        return typeOfPayment;
    }

    public String getMemberId()
    {
        return memberId;
    }

    public int getPaymentId()
    {
        return paymentId;
    }

}
