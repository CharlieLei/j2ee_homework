package com.example.yummy.dao;

import com.example.yummy.model.member.Member;
import com.example.yummy.model.member.MemberState;
import com.example.yummy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository("memberDao")
public class MemberDaoImpl implements MemberDao {
    @Override
    public void add(Member member) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(member);
        transaction.commit();
    }

    @Override
    public boolean activateMember(String memberId, String code) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Member member = session.get(Member.class, memberId);

        if (member != null) {
            member.setState(MemberState.VALID);
            session.merge(member);
            transaction.commit();
            return true;
        }else {
            transaction.commit();
            return false;
        }
    }

    @Override
    public void cancel(String memberId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Member member = session.get(Member.class, memberId);
        member.setState(MemberState.CANCELLED);
        session.merge(member);

        transaction.commit();
    }

    @Override
    public boolean isLoginInfoCorrect(String memberId, String password) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        TypedQuery<Member> query = session.createQuery(
                "select m from Member m where m.memberId = ?1 and m.password = ?2 and m.state = ?3",
                Member.class
        );
        query.setParameter(1, memberId);
        query.setParameter(2, password);
        query.setParameter(3, MemberState.VALID);

        List<Member> list = query.getResultList();
        transaction.commit();

        return list.size() == 1;
    }

    @Override
    public Member get(String memberId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Member member = session.get(Member.class, memberId);
        transaction.commit();

        return member;
    }
}
