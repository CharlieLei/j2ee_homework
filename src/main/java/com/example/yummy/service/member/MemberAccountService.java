package com.example.yummy.service.member;

public interface MemberAccountService {
    public void register(String id, String password, String email);

    public boolean activateMember(String code);

    public void cancel(String memberId);
}
