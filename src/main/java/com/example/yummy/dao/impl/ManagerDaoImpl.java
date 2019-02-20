package com.example.yummy.dao.impl;

import com.example.yummy.dao.ManagerDao;
import com.example.yummy.model.manager.Manager;
import com.example.yummy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ManagerDaoImpl implements ManagerDao {
    @Override
    public boolean isLoginInfoCorrect(String managerId, String password) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        TypedQuery<Manager> query = session.createQuery(
                "select m from Manager m where m.id = ?1 and m.password = ?2",
                Manager.class
        );
        query.setParameter(1, managerId);
        query.setParameter(2, password);

        List<Manager> list = query.getResultList();
        transaction.commit();

        return list.size() == 1;
    }

    @Override
    public Manager get(String managerId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Manager manager = session.get(Manager.class, managerId);
        transaction.commit();

        return manager;
    }
}
