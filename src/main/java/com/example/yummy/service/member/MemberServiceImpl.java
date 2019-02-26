package com.example.yummy.service.member;

import com.example.yummy.dao.member.MemberDao;
import com.example.yummy.model.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member get(String memberId) {
        return memberDao.get(memberId);
    }
}
