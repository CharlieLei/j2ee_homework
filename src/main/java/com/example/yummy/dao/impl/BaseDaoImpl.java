package com.example.yummy.dao.impl;

import com.example.yummy.dao.BaseDao;
import com.example.yummy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

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
