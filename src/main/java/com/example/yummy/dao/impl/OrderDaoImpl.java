package com.example.yummy.dao.impl;

import com.example.yummy.dao.BaseDao;
import com.example.yummy.dao.OrderDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderState;
import com.example.yummy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private BaseDao baseDao = DaoFactory.getBaseDao();

    @Override
    public boolean add(Order order) {
        Order newOrder = new Order();
        baseDao.save(newOrder);
        order.setId(newOrder.getId());
        return baseDao.update(order);
    }

    @Override
    public boolean delete(int orderId) {
        return false;
    }

    @Override
    public boolean modify(Order order) {
        return baseDao.update(order);
    }

    @Override
    public Order get(int orderId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Order order = session.get(Order.class, orderId);
        transaction.commit();

        return order;
    }

    @Override
    public List<Order> getAllDeliveringOrder(String memberId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Order> query = session.createQuery(
                "select o from Order o where o.memberId = ?1 and o.state = ?2",
                Order.class
        );
        query.setParameter(1, memberId);
        query.setParameter(2, OrderState.DELIVERING);

        List<Order> list = query.getResultList();
        transaction.commit();

        return list;
    }

    @Override
    public List<Order> getAllOrdersOfThisRestaurant(String restaurantId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Order> query = session.createQuery(
                "select o from Order o where o.restaurantId = ?1",
                Order.class
        );
        query.setParameter(1, restaurantId);

        List<Order> list = query.getResultList();
        transaction.commit();

        return list;
    }
}
