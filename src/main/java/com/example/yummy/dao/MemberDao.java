package com.example.yummy.dao;


import com.example.yummy.model.member.Member;

import java.util.List;

public interface MemberDao {

    public boolean isLoginInfoCorrect(String memberId, String password);

    public boolean add(Member member);

    /**
     * 激活会员
     * @param code 激活码
     */
    public boolean activate(String memberId, String code);

    /**
     * 注销会员
     * @param memberId 会员ID
     */
    public boolean delete(String memberId);

    public boolean modify(Member member);

    public Member get(String memberId);

    public List<Member> getAll();
}
