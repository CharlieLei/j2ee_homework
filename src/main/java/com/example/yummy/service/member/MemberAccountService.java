package com.example.yummy.service.member;

import com.example.yummy.model.Address;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.member.MemberInfo;

public interface MemberAccountService {
    public boolean register(String memberId, String password, MemberInfo memberInfo);

    public boolean activate(String memberId, String code);

    public boolean login(String memberId, String password);

    public boolean cancel(String memberId);

    public boolean modifyInfo(String memberId, MemberInfo memberInfo);

    public boolean addDeliveryAddr(String memberId, Address address);
}
