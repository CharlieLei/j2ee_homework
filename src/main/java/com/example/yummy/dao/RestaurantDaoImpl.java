package com.example.yummy.dao;

import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RestaurantDaoImpl implements RestaurantDao {
    @Override
    public boolean isLoginInfoCorrect(String restaurantId, String password) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Restaurant> query = session.createQuery(
                "select r from Restaurant r where r.restaurantId = ?1 and r.password = ?2",
                Restaurant.class
        );
        query.setParameter(1, restaurantId);
        query.setParameter(2, password);

        List<Restaurant> list = query.getResultList();
        transaction.commit();

        return list.size() == 1;
    }

    @Override
    public boolean add(Restaurant restaurant) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(restaurant);
        transaction.commit();

        return true;
    }

    @Override
    public boolean modify(Restaurant restaurant) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(restaurant);
        transaction.commit();

        return true;
    }

    @Override
    public Restaurant get(String restaurantId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Restaurant restaurant = session.get(Restaurant.class, restaurantId);
        transaction.commit();

        return restaurant;
    }
}
