package com.example.yummy.service.member;

import com.example.yummy.dao.member.MemberDao;
import com.example.yummy.model.Address;
import com.example.yummy.model.member.*;
import com.example.yummy.util.StringUtil;
import com.example.yummy.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberAccountServiceImpl implements MemberAccountService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public boolean register(String memberId, String password, MemberInfo memberInfo) {
        memberInfo.setLevel(MemberLevel.COPPER);
        memberInfo.setBalance(0);

        Member member = new Member();
        member.setId(memberId);
        member.setPassword(password);
        member.setState(MemberState.UNVERIFIED);
        member.setMemberInfo(memberInfo);
        //生成激活码
        String code = StringUtil.generateUniqueCode();
        member.setCode(code);

        boolean result = memberDao.add(member);

        if (result) {
            //通过线程的方式给会员发送一封邮件
            new Thread(new MailUtil(member.getMemberInfo().getEmail(), member.getId(), code)).start();
        }

        return result;
    }

    @Override
    public boolean activate(String memberId, String code) {
        return memberDao.activate(memberId, code);
    }

    @Override
    public boolean login(String memberId, String password) {
        return memberDao.isLoginInfoCorrect(memberId, password);
    }

    @Override
    public boolean cancel(String memberId) {
        return memberDao.delete(memberId);
    }

    @Override
    public boolean modifyInfo(String memberId, MemberInfo memberInfo) {
        Member member = memberDao.get(memberId);
        member.setMemberInfo(memberInfo);
        return memberDao.modify(member);
    }

    @Override
    public boolean addDeliveryAddr(String memberId, Address address) {
        Member member = memberDao.get(memberId);

        MemberDeliveryAddress deliveryAddress = new MemberDeliveryAddress();
        deliveryAddress.setMemberId(memberId);
        deliveryAddress.setAddress(address);
        member.getDeliveryAddrList().add(deliveryAddress);

        return memberDao.modify(member);
    }
}
