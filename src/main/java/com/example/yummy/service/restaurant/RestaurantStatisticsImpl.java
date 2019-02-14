package com.example.yummy.service.restaurant;

import com.example.yummy.model.order.Order;
import com.example.yummy.model.restaurant.RestaurantType;

import java.sql.Timestamp;
import java.util.List;

public class RestaurantStatisticsImpl implements RestaurantStatisticsService {
    @Override
    public List<Order> getDeliveryOrders(long restaurantId, Timestamp startTime, Timestamp endTime, double lowerAmount, double upperAmount, RestaurantType restaurantType) {
        return null;
    }
}
