/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.services;

import com.esd.cw.dao.UserDao;
import com.esd.cw.model.User;
import java.util.List;

/**
 *
 * @author aidanayala-angear
 */
public class AdminDashboardService {
    
    UserDao userDao = new UserDao();
    List<User> users;
    
    public AdminDashboardService() { 
        users = userDao.findAllNonAdminUsers();
    }

    public List<User> getUsers() {
        return users;
    }
}
