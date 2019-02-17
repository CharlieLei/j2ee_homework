package com.example.yummy.service.member;

import com.example.yummy.dao.MemberDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.member.Member;

public class MemberServiceImpl implements MemberService {
    @Override
    public Member get(String memberId) {
        MemberDao memberDao = DaoFactory.getMemberDao();
        return memberDao.get(memberId);
    }
}
