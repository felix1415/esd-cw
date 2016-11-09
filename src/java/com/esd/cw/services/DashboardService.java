/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.services;

import com.esd.cw.dao.MemberDao;
import com.esd.cw.model.Member;
import com.esd.cw.model.User;

/**
 *
 * @author alexgray
 */
public class DashboardService
{
    MemberDao memberDao;

    public DashboardService() {
        this.memberDao = new MemberDao();
    }
    
    public Member getMember(User user) {
        Member member = memberDao.findById(user.getUserId());

        if (member.getMemberId() == null) {
            return null;
        } else {
            return member;
        }
    }
}
