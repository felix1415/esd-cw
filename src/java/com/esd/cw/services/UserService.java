/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.services;

import com.esd.cw.dao.UserDao;
import com.esd.cw.model.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shaun
 */
public class UserService {

    UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public User login(String username, String password) throws SQLException {
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

    public User getUser(String userId) {
        return userDao.findById(userId);
    }
    
    public List<User> getUsersWithIdContaining(String toMatch) throws SQLException{
        if(toMatch == null){
            return userDao.findAllNonAdminUsers();
        }else{
            return userDao.getAllUsersWithIdConatining(toMatch);
        }
    }
}
