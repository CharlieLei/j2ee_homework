package com.example.yummy.dao;

import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderState;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderDao {
    public boolean add(Order order);

    public boolean delete(int orderId);

    public boolean changeState(int orderId, OrderState state);

    public List<Order> getAllDeliveryingOrder(String memberId);

    public List<Order> getAllOrdersOfThisRestaurant(String restaurantId);
}
