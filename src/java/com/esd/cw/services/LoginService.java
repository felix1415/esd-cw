/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.services;

import com.esd.cw.dao.UserDao;
import com.esd.cw.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shaun
 */
public class LoginService {
    UserDao userDao;

    public LoginService() {
        this.userDao = new UserDao();
    }
    
    public User login(String username, String password) {
        User user = userDao.findById(username);

        if (user.getUserId() == null) {
            return null;
        }

        if (user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }

    }
}
