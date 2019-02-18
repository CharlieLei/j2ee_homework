package com.example.yummy.dao.impl;

import com.example.yummy.dao.BaseDao;
import com.example.yummy.dao.YummyDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.yummyBill.YummyBill;
import com.example.yummy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class YummyDaoImpl implements YummyDao {

    private BaseDao baseDao = DaoFactory.getBaseDao();

    @Override
    public boolean add(YummyBill yummyBill) {
        return baseDao.save(yummyBill);
    }

    @Override
    public boolean modify(YummyBill yummyBill) {
        return baseDao.update(yummyBill);
    }

    @Override
    public List<YummyBill> getAllUnsettledBills() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<YummyBill> query = session.createQuery(
                "select y from YummyBill y where y.isSettled = false ",
                YummyBill.class
        );
        List<YummyBill> list = query.getResultList();
        transaction.commit();

        return list;
    }
}
