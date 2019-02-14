package com.example.yummy.service.member;

import com.example.yummy.dao.MemberDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.member.Member;
import com.example.yummy.util.CodeUtil;
import com.example.yummy.util.MailUtil;

public class MemberAccountImpl implements MemberAccountService {

    @Override
    public void register(String id, String password, String email) {
        //生成激活码
        String code = CodeUtil.generateUniqueCode();

        MemberDao memberDao = DaoFactory.getMemberDao();
        memberDao.save(new Member(id, password, email, code));

        //通过线程的方式给会员发送一封邮件
        new Thread(new MailUtil(email, code)).start();

    }

    @Override
    public boolean activateMember(String code) {
        MemberDao memberDao = DaoFactory.getMemberDao();
        return memberDao.activateMember(code);
    }

    @Override
    public void cancel(String memberId) {
        MemberDao memberDao = DaoFactory.getMemberDao();
        memberDao.cancelMember(memberId);
    }
}
