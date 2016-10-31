/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.model;

/**
 *
 * @author alexgray
 */
public class User
{
    
    private String userId;
    private String password;
    private String userStatus;

    public String getUserStatus()
    {
        return userStatus;
    }

    public void setUserStatus(String userStatus)
    {
        this.userStatus = userStatus;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUserId()
    {
        return userId;
    }

}
