/*
 * Stephen Turner, Computer Science BSc Year 3
 * University Of the West Of England
 */
package com.esd.cw.services;

import com.esd.cw.dao.ClaimDao;
import com.esd.cw.dao.MemberDao;
import com.esd.cw.dao.UserDao;

/**
 *
 * @author sturner
 */
public class ClaimService {

    ClaimDao claimDao = new ClaimDao();
    MemberDao memberDao = new MemberDao();
    UserDao userDao = new UserDao();
    
}
