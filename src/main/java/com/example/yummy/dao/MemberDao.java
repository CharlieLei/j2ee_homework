package com.example.yummy.dao;


import com.example.yummy.model.member.Member;

public interface MemberDao {
    /**
     * 保存未验证会员的信息
     * @param member 未验证会员
     */
    public void save(Member member);

    /**
     * 激活会员
     * @param code 激活码
     */
    public boolean activateMember(String memberId, String code);

    /**
     * 注销会员
     * @param memberId 会员ID
     */
    public void cancelMember(String memberId);

    public boolean isLoginInfoCorrect(String memberId, String password);

    public Member getMember(String memberId);
}
