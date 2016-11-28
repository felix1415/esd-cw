package com.esd.cw.services;

import com.esd.cw.dao.MemberDao;
import com.esd.cw.dao.UserDao;
import com.esd.cw.model.Member;
import com.esd.cw.util.PropertiesUtil;

public class MemberService {

    MemberDao memberDao;
    UserDao userDao;

    public MemberService() {
        this.memberDao = new MemberDao();
        this.userDao = new UserDao();
    }

    public boolean claimBalanceCheck(String memId, double claimAmount) {

        Member member = this.memberDao.findById(memId);

        double excess = claimAmount / 100 * Double.valueOf(PropertiesUtil.getPropertyAsString("claim-percentage"));

        return (excess < member.getBalance());

    }

    public Member getMember(String memberId) {
        return memberDao.findById(memberId);
    }
}
