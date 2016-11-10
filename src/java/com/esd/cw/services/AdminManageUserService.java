/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.services;

import com.esd.cw.dao.UserDao;
import com.esd.cw.model.User;

/**
 *
 * @author aidanayala-angear
 */
public class AdminManageUserService {
    
    String userId;
    UserDao userDao;
    User user;
    
    public AdminManageUserService(String userId) {
        this.userId = userId;
        this.userDao = new UserDao();
        this.user = this.userDao.findById(this.userId);
    }
    
    public User getUser() {
        return user;
    }
}
