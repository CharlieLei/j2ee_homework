package com.example.yummy.dao;

import com.example.yummy.model.member.Member;
import org.springframework.stereotype.Repository;

@Repository("memberDao")
public class MemberDaoImpl implements MemberDao {
    @Override
    public void save(Member member) {

    }

    @Override
    public boolean activateMember(String code) {
        return true;
    }

    @Override
    public void cancelMember(String memberId) {

    }

    @Override
    public boolean isLoginInfoCorrect(String memberId, String password) {
        return false;
    }

    @Override
    public Member getMember(String memberId) {
        return new Member("id1", "123456", "qwer@sdf.com", "code1");
    }
}
