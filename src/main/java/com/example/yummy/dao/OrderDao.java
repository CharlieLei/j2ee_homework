package com.example.yummy.dao;

import com.example.yummy.model.member.MemberLevel;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderState;
import com.example.yummy.model.restaurant.RestaurantType;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

public interface OrderDao {
    public boolean add(Order order);

    public boolean delete(int orderId);

    public boolean modify(Order order);

    public Order get(int orderId);

    public List<Order> getAllOrder(String memberId, OrderState orderState);

    public List<Order> getAllOrder(String memberId,
                                   Timestamp startTime, Timestamp endTime,
                                   double lowerAmount, double upperAmount,
                                   OrderState orderState);

    public List<Order> getAllOrdersOfThisRestaurant(String restaurantId, OrderState orderState);

    public List<Order> getAllOrdersOfThisRestaurant(String restaurantId,
                                                    Timestamp startTime, Timestamp endTime,
                                                    double lowerAmount, double upperAmount,
                                                    OrderState orderState);
}
