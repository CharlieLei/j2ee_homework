package com.example.yummy.service.order;

import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderState;

import java.util.List;

public interface OrderService {

    public Order get(int orderId);

    public boolean place(Order order);

    public boolean cancel(int orderId);

    public boolean complete(int orderId);

    public List<Order> getAllOrders(String memberId, OrderState orderState);

    public List<Order> getAllOrdersOfThisRestaurant(String restaurantId, OrderState orderState);
}
