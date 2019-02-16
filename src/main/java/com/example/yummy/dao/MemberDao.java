package com.example.yummy.dao;


import com.example.yummy.model.member.Member;

public interface MemberDao {

    public void add(Member member);

    /**
     * 激活会员
     * @param code 激活码
     */
    public boolean activateMember(String memberId, String code);

    /**
     * 注销会员
     * @param memberId 会员ID
     */
    public void cancel(String memberId);

    public boolean isLoginInfoCorrect(String memberId, String password);

    public Member get(String memberId);
}
