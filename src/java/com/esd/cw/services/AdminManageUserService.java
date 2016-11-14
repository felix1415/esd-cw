/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.services;

import com.esd.cw.dao.UserDao;
import com.esd.cw.model.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aidanayala-angear
 */
public class AdminManageUserService {
   
    private final UserDao userDao = new UserDao();
    
    public AdminManageUserService() {
        
    }
    
    public User getUser(String userId) {
        return userDao.findById(userId);
    }
    
    public void updateUserStatus(String userId, String status) {
        User user = userDao.findById(userId);
        user.setUserStatus(status);
        try {
            userDao.updateUserStatus(user);
        } catch (SQLException ex) {
            Logger.getLogger(AdminManageUserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
