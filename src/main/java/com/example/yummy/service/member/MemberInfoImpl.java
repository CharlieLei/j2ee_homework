package com.example.yummy.service.member;

import com.example.yummy.dao.MemberDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.member.Member;

public class MemberInfoImpl implements MemberInfoService {
    @Override
    public Member getMember(String memberId) {
        MemberDao memberDao = DaoFactory.getMemberDao();
        return memberDao.getMember(memberId);
    }

    public void changePhone(String memberId, String newPhone) {

    }

    public void changeName(String memberId, String newName) {

    }

    public void addDeliveryAddr() {

    }

    public void removeDeliveryAddr() {

    }

    public void modifyDeliveryAddr() {

    }
}
