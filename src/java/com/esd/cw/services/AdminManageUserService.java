/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.services;

import com.esd.cw.dao.MemberDao;
import com.esd.cw.dao.UserDao;
import com.esd.cw.model.Member;
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
    private final MemberDao memberDao = new MemberDao();
    
    public AdminManageUserService() {
        
    }
    
    public User getUser(String userId) throws SQLException {
        return userDao.findById(userId);
    }
    
    public void updateUserStatus(String userId, String status) throws SQLException {
        User user = userDao.findById(userId);
        user.setUserStatus(status);
        Member member = memberDao.findById(userId);
        member.setStatus(status);
        
        try {
            userDao.updateUserStatus(user);
        } catch (SQLException ex) {
            Logger.getLogger(AdminManageUserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            memberDao.updateMemberStatus(member);
        } catch (SQLException ex) {
            Logger.getLogger(AdminManageUserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
