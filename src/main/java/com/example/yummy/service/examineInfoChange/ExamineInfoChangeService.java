package com.example.yummy.service.examineInfoChange;

import com.example.yummy.model.restaurant.Restaurant;

import java.util.List;

public interface ExamineInfoChangeService {
    public List<Restaurant> getAllRestaurantInfoChanges();

    public boolean approveRestaurantInfoChange(String restaurantId);
}
