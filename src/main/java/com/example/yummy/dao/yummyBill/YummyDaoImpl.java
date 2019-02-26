package com.example.yummy.dao.yummyBill;

import com.example.yummy.dao.BaseDao;
import com.example.yummy.model.yummyBill.YummyBill;
import com.example.yummy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class YummyDaoImpl implements YummyDao {

    @Autowired
    private BaseDao baseDao;

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
        session.close();

        return list;
    }

    @Override
    public List<YummyBill> getAllSettledBills(Timestamp startTime, Timestamp endTime) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<YummyBill> query = session.createQuery(
                "select y from YummyBill y where y.isSettled = true and (y.tradingDate between ?1 and ?2)",
                YummyBill.class
        );
        query.setParameter(1, startTime);
        query.setParameter(2, endTime);

        List<YummyBill> list = query.getResultList();
        transaction.commit();
        session.close();

        return list;
    }
}
