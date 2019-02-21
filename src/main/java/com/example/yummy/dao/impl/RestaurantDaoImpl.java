package com.example.yummy.dao.impl;

import com.example.yummy.dao.BaseDao;
import com.example.yummy.dao.RestaurantDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantInfoChange;
import com.example.yummy.model.restaurant.RestaurantType;
import com.example.yummy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantDaoImpl implements RestaurantDao {

    private BaseDao baseDao = DaoFactory.getBaseDao();

    @Override
    public boolean isLoginInfoCorrect(String restaurantId, String password) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Restaurant> query = session.createQuery(
                "select r from Restaurant r where r.id = ?1 and r.password = ?2",
                Restaurant.class
        );
        query.setParameter(1, restaurantId);
        query.setParameter(2, password);

        List<Restaurant> list = query.getResultList();
        transaction.commit();
        session.close();

        return list.size() == 1;
    }

    @Override
    public boolean add(Restaurant restaurant) {
        return baseDao.save(restaurant);
    }

    @Override
    public boolean modify(Restaurant restaurant) {
        return baseDao.update(restaurant);
    }

    @Override
    public Restaurant get(String restaurantId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Restaurant restaurant = session.get(Restaurant.class, restaurantId);
        transaction.commit();
        session.close();

        return restaurant;
    }

    @Override
    public List<Restaurant> getRestaurantsByType(RestaurantType type) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        List<Restaurant> list;
        if (type == null) {
            TypedQuery<Restaurant> query = session.createQuery(
                    "select r from Restaurant r",
                    Restaurant.class
            );
            list = query.getResultList();
        }else {
            TypedQuery<Restaurant> query = session.createQuery(
                    "select r from Restaurant r where r.restaurantInfo.restaurantType = ?1",
                    Restaurant.class
            );
            query.setParameter(1, type);
            list = query.getResultList();
        }
        transaction.commit();
        session.close();

        List<Restaurant> newList = new ArrayList<>();
        for (Restaurant restaurant: list) {
            if (restaurant.getAddress() != null) {
                newList.add(restaurant);
            }
        }

        return newList;
    }
}
