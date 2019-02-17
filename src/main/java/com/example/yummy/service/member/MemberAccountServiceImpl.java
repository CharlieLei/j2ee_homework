package com.example.yummy.service.member;

import com.example.yummy.dao.MemberDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.member.Member;
import com.example.yummy.util.StringUtil;
import com.example.yummy.util.MailUtil;

public class MemberAccountServiceImpl implements MemberAccountService {

    @Override
    public void register(Member member) {
        //生成激活码
        String code = StringUtil.generateUniqueCode();
        member.setCode(code);

        MemberDao memberDao = DaoFactory.getMemberDao();
        memberDao.add(member);

        //通过线程的方式给会员发送一封邮件
        new Thread(new MailUtil(member.getEmail(), member.getId(), code)).start();
    }

    @Override
    public boolean activate(String memberId, String code) {
        MemberDao memberDao = DaoFactory.getMemberDao();
        return memberDao.activate(memberId, code);
    }

    @Override
    public boolean login(String memberId, String password) {
        return false;
    }

    @Override
    public boolean cancel(String memberId) {
        MemberDao memberDao = DaoFactory.getMemberDao();
        memberDao.delete(memberId);
        return false;
    }

    @Override
    public boolean modifyInfo(Member member) {
        return false;
    }
}
