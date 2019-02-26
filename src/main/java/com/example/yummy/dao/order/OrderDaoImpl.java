package com.example.yummy.dao.order;

import com.example.yummy.dao.BaseDao;
import com.example.yummy.dao.food.FoodCombinationDao;
import com.example.yummy.dao.food.FoodItemDao;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderFoodCombination;
import com.example.yummy.model.order.OrderFoodItem;
import com.example.yummy.model.order.OrderState;
import com.example.yummy.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private BaseDao baseDao;
    @Autowired
    private FoodItemDao foodItemDao;
    @Autowired
    private FoodCombinationDao foodCombinationDao;

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
        this.initializeListInOrder(order);
        transaction.commit();
        session.close();

        return order;
    }

    @Override
    public List<Order> getAllOrder(String memberId, OrderState orderState) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Order> query;
        if (orderState == OrderState.NOT_ACTIVE){
            query = session.createQuery(
                    "select o from Order o where o.memberId = ?1 and o.state <> ?2 and o.state <> ?3",
                    Order.class
            );
            query.setParameter(1, memberId);
            query.setParameter(2, OrderState.PAYING);
            query.setParameter(3, OrderState.DELIVERING);
        }else {
            query = session.createQuery(
                    "select o from Order o where o.memberId = ?1 and o.state = ?2",
                    Order.class
            );
            query.setParameter(1, memberId);
            query.setParameter(2, orderState);
        }

        List<Order> list = query.getResultList();
        for (Order order: list) {
            this.initializeListInOrder(order);
        }
        transaction.commit();
        session.close();

        return list;
    }

    @Override
    public List<Order> getAllOrder(String memberId, Timestamp startTime, Timestamp endTime, double lowerAmount, double upperAmount, OrderState orderState) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Order> query;
        if (orderState == null) {
            query = session.createQuery(
                    "select o from Order o " +
                            "where o.memberId = ?1 and (o.placingOrderTime between ?2 and ?3) and" +
                            "(o.totalAmount between ?4 and ?5)",
                    Order.class
            );
            query.setParameter(1, memberId);
            query.setParameter(2, startTime);
            query.setParameter(3, endTime);
            query.setParameter(4, lowerAmount);
            query.setParameter(5, upperAmount);
        }else {
            query = session.createQuery(
                    "select o from Order o where o.memberId = ?1 and (o.placingOrderTime between ?2 and ?3) and" +
                            "(o.totalAmount between ?4 and ?5) and o.state = ?6",
                    Order.class
            );
            query.setParameter(1, memberId);
            query.setParameter(2, startTime);
            query.setParameter(3, endTime);
            query.setParameter(4, lowerAmount);
            query.setParameter(5, upperAmount);
            query.setParameter(6, orderState);
        }
        List<Order> list = query.getResultList();
        for (Order order: list) {
            this.initializeListInOrder(order);
        }
        transaction.commit();
        session.close();

        return list;
    }

    @Override
    public List<Order> getAllOrdersOfThisRestaurant(String restaurantId, OrderState orderState) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Order> query;
        if (orderState == OrderState.NOT_ACTIVE) {
            query = session.createQuery(
                    "select o from Order o where o.restaurantId = ?1 and o.state <> ?2 and o.state <> ?3",
                    Order.class
            );
            query.setParameter(1, restaurantId);
            query.setParameter(2, OrderState.PAYING);
            query.setParameter(3, OrderState.DELIVERING);
        }else {
            query = session.createQuery(
                    "select o from Order o where o.restaurantId = ?1 and o.state = ?2",
                    Order.class
            );
            query.setParameter(1, restaurantId);
            query.setParameter(2, orderState);
        }
        List<Order> list = query.getResultList();
        for (Order order: list) {
            this.initializeListInOrder(order);
        }
        transaction.commit();
        session.close();

        return list;
    }

    @Override
    public List<Order> getAllOrdersOfThisRestaurant(String restaurantId,
                                                    Timestamp startTime, Timestamp endTime,
                                                    double lowerAmount, double upperAmount,
                                                    OrderState orderState) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Order> query;
        if (orderState == null) {
            query = session.createQuery(
                    "select o from Order o " +
                            "where o.restaurantId = ?1 and (o.placingOrderTime between ?2 and ?3) and " +
                            "(o.totalAmount between ?4 and ?5)",
                    Order.class
            );
            query.setParameter(1, restaurantId);
            query.setParameter(2, startTime);
            query.setParameter(3, endTime);
            query.setParameter(4, lowerAmount);
            query.setParameter(5, upperAmount);
        }else {
            query = session.createQuery(
                    "select o from Order o where o.restaurantId = ?1 and (o.placingOrderTime between ?2 and ?3) and" +
                            "(o.totalAmount between ?4 and ?5) and o.state = ?6",
                    Order.class
            );
            query.setParameter(1, restaurantId);
            query.setParameter(2, startTime);
            query.setParameter(3, endTime);
            query.setParameter(4, lowerAmount);
            query.setParameter(5, upperAmount);
            query.setParameter(6, orderState);
        }
        List<Order> list = query.getResultList();
        for (Order order: list) {
            this.initializeListInOrder(order);
        }
        transaction.commit();
        session.close();

        return list;
    }

    private void initializeListInOrder(Order order) {
        List<OrderFoodItem> foodItemList = order.getOrderFoodItemList();
        Hibernate.initialize(foodItemList);

        List<OrderFoodCombination> foodCombinationList = order.getOrderFoodCombinationList();
        Hibernate.initialize(foodCombinationList);
    }
}
