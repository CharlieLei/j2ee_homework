package com.example.yummy.dao.impl;

import com.example.yummy.dao.BaseDao;
import com.example.yummy.dao.RestaurantInfoChangeDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.restaurant.RestaurantInfoChange;
import com.example.yummy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class RestaurantInfoChangeDaoImpl implements RestaurantInfoChangeDao {

    private BaseDao baseDao = DaoFactory.getBaseDao();

    @Override
    public boolean add(RestaurantInfoChange restaurantInfoChange) {
        return baseDao.save(restaurantInfoChange);
    }

    @Override
    public boolean modify(RestaurantInfoChange restaurantInfoChange) {
        return baseDao.update(restaurantInfoChange);
    }

    @Override
    public RestaurantInfoChange getUnexamined(String restaurantId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<RestaurantInfoChange> query = session.createQuery(
                "select r from RestaurantInfoChange r where r.restaurantId = ?1 and r.isExamined = false",
                RestaurantInfoChange.class
        );
        query.setParameter(1, restaurantId);
        List<RestaurantInfoChange> list = query.getResultList();

        transaction.commit();

        if (list.size() == 1) {
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public List<RestaurantInfoChange> getAllUnexaminedInfoChanges() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<RestaurantInfoChange> query = session.createQuery(
                "select r from RestaurantInfoChange r where r.isExamined = false",
                RestaurantInfoChange.class
        );
        List<RestaurantInfoChange> list = query.getResultList();

        transaction.commit();

        return list;
    }
}
