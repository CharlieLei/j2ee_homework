package com.example.yummy.dao.impl;

import com.example.yummy.dao.BaseDao;
import com.example.yummy.dao.CouponDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.coupon.Coupon;
import com.example.yummy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class CouponDaoImpl implements CouponDao {

    private BaseDao baseDao = DaoFactory.getBaseDao();

    @Override
    public boolean add(Coupon coupon) {
        return baseDao.save(coupon);
    }

    @Override
    public List<Coupon> getCouponListByRestaurant(String restaurantId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        TypedQuery<Coupon> query = session.createQuery(
                "select c from Coupon c where c.restaurantId = ?1",
                Coupon.class
        );
        query.setParameter(1, restaurantId);

        List<Coupon> list = query.getResultList();
        transaction.commit();
        session.close();

        return list;
    }

    @Override
    public List<Coupon> getCouponListByMember(String memberId) {
        return null;
    }
}
