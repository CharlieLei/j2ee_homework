package com.example.yummy.service.order;

import com.example.yummy.model.order.Order;

import java.util.List;

public interface OrderService {
    public boolean place(Order order);

    public boolean pay(int orderId);

    public boolean withdraw(int orderId);

    public boolean complete(int orderId);

    public List<Order> getAllDeliveringOrders(String memberId);

    public List<Order> getAllOrdersOfThisRestaurant(String restaurantId);
}
