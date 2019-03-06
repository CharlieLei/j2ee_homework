package com.example.yummy.dao.restaurant;

import com.example.yummy.dao.BaseDao;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantState;
import com.example.yummy.model.restaurant.RestaurantType;
import com.example.yummy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantDaoImpl implements RestaurantDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public boolean isLoginInfoCorrect(String restaurantId, String password) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Restaurant> query = session.createQuery(
                "select r from Restaurant r where r.id = ?1 and r.password = ?2 and r.state = :state",
                Restaurant.class
        );
        query.setParameter(1, restaurantId);
        query.setParameter(2, password);
        query.setParameter("state", RestaurantState.ACTIVATED);

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
    public List<Restaurant> getRestaurantsByType(RestaurantType type, RestaurantState state) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        List<Restaurant> list;
        if (type == null) {
            TypedQuery<Restaurant> query = session.createQuery(
                    "select r from Restaurant r where r.state = :state",
                    Restaurant.class
            );
            query.setParameter("state", state);
            list = query.getResultList();
        }else {
            TypedQuery<Restaurant> query = session.createQuery(
                    "select r from Restaurant r where r.restaurantInfo.restaurantType = ?1 and r.state = :state",
                    Restaurant.class
            );
            query.setParameter(1, type);
            query.setParameter("state", state);
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
