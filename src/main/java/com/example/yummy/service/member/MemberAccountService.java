package com.example.yummy.service.member;

import com.example.yummy.model.member.Member;

public interface MemberAccountService {
    public void register(Member member);

    public boolean activateMember(String code);

    public boolean login(String memberId, String password);

    public void cancel(String memberId);
}
