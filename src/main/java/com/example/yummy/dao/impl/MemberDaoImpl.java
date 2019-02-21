package com.example.yummy.dao.impl;

import com.example.yummy.dao.BaseDao;
import com.example.yummy.dao.MemberDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.member.MemberState;
import com.example.yummy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberDaoImpl implements MemberDao {

    private BaseDao baseDao = DaoFactory.getBaseDao();

    @Override
    public boolean isLoginInfoCorrect(String memberId, String password) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        TypedQuery<Member> query = session.createQuery(
                "select m from Member m where m.id = ?1 and m.password = ?2 and m.state = ?3",
                Member.class
        );
        query.setParameter(1, memberId);
        query.setParameter(2, password);
        query.setParameter(3, MemberState.VALID);

        List<Member> list = query.getResultList();
        transaction.commit();
        session.close();

        return list.size() == 1;
    }

    @Override
    public boolean add(Member member) {
        return baseDao.save(member);
    }

    @Override
    public boolean activate(String memberId, String code) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Member member = session.get(Member.class, memberId);

        if (member != null) {
            member.setState(MemberState.VALID);
            session.merge(member);
            transaction.commit();
            session.close();
            return true;
        }else {
            transaction.commit();
            session.close();
            return false;
        }
    }

    @Override
    public boolean delete(String memberId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Member member = session.get(Member.class, memberId);
        member.setState(MemberState.CANCELLED);
        session.merge(member);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean modify(Member member) {
        return baseDao.update(member);
    }

    @Override
    public Member get(String memberId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Member member = session.get(Member.class, memberId);
        transaction.commit();
        session.close();

//        if (member.getDeliveryAddrList() == null) {
//            member.setDeliveryAddrList(new ArrayList<>());
//        }

        return member;
    }
}
