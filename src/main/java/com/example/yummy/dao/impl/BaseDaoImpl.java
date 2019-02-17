package com.example.yummy.dao.impl;

import com.example.yummy.dao.BaseDao;
import com.example.yummy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BaseDaoImpl implements BaseDao {
    @Override
    public boolean save(Object bean) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(bean);
        transaction.commit();

        return true;
    }

    @Override
    public boolean update(Object bean) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(bean);
        transaction.commit();

        return true;
    }
}
