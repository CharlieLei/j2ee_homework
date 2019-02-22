package com.example.yummy.service.member;

import com.example.yummy.model.restaurant.RestaurantType;
import com.example.yummy.model.statistics.MemberStatistics;

import java.sql.Timestamp;

public interface MemberStatisticsService {
    public MemberStatistics get(String memberId,
                                Timestamp startTime, Timestamp endTime,
                                double lowerAmount, double upperAmount,
                                RestaurantType restaurantType);
}
