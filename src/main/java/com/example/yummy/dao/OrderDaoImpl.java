package com.example.yummy.dao;

import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderState;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean add(Order order) {
        return false;
    }

    @Override
    public boolean delete(int orderId) {
        return false;
    }

    @Override
    public boolean changeState(int orderId, OrderState state) {
        return false;
    }

    @Override
    public List<Order> getAllDeliveryingOrder(String memberId) {
        return null;
    }

    @Override
    public List<Order> getAllOrdersOfThisRestaurant(String restaurantId) {
        return null;
    }
}
