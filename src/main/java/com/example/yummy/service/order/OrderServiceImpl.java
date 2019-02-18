package com.example.yummy.service.order;

import com.example.yummy.dao.OrderDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.order.Order;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public boolean place(Order order) {
        OrderDao orderDao = DaoFactory.getOrderDao();
        return orderDao.add(order);
    }

    @Override
    public boolean pay(int orderId) {
        return false;
    }

    @Override
    public boolean withdraw(int orderId) {
        return false;
    }

    @Override
    public List<Order> getAllDeliveringOrders(String memberId) {
        return null;
    }

    @Override
    public List<Order> getAllOrdersOfThisRestaurant(String restaurantId) {
        return null;
    }
}
