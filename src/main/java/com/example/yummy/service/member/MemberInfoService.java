package com.example.yummy.service.member;


import com.example.yummy.model.member.Member;

public interface MemberInfoService {
    public Member getMember(String memberId);

    public void changePhone(String memberId, String newPhone);

    public void changeName(String memberId, String newName);

    public void addDeliveryAddr();

    public void removeDeliveryAddr();

    public void modifyDeliveryAddr();
}
