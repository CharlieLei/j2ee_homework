package com.example.yummy.dao;

import com.example.yummy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;


@Repository(value = "baseDao")
public class BaseDaoImpl implements BaseDao {

    @Override
    public boolean save(Object bean) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(bean);
        session.flush();
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean merge(Object bean) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(bean);
        session.flush();
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Object bean) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(bean);
        session.flush();
        transaction.commit();
        session.close();

        return true;
    }
}
