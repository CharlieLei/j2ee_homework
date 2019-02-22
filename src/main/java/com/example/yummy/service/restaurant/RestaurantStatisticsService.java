package com.example.yummy.service.restaurant;

import com.example.yummy.model.member.MemberLevel;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.restaurant.RestaurantType;
import com.example.yummy.model.statistics.RestaurantStatistics;

import java.sql.Timestamp;
import java.util.List;

public interface RestaurantStatisticsService {

    public RestaurantStatistics get(String restaurantId,
                                    Timestamp startTime, Timestamp endTime,
                                    double lowerAmount, double upperAmount,
                                    MemberLevel memberLevel);
}
