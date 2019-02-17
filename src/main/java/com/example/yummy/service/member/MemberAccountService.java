package com.example.yummy.service.member;

import com.example.yummy.model.member.Member;

public interface MemberAccountService {
    public void register(Member member);

    public boolean activate(String memberId, String code);

    public boolean login(String memberId, String password);

    public boolean cancel(String memberId);

    public boolean modifyInfo(Member member);
}
