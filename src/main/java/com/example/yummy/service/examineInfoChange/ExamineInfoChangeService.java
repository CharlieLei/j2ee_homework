package com.example.yummy.service.examineInfoChange;

import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantInfoChange;

import java.util.List;

public interface ExamineInfoChangeService {
    public List<RestaurantInfoChange> getAllUnexaminedRestaurantInfoChanges();

    public boolean approveRestaurantInfoChange(int id, String restaurantId);

    public boolean disapproveRestaurantInfoChange(int id, String restaurantId);

    public List<Restaurant> getAllUnactivatedRestaurants();

    public boolean approveRestaurant(String restaurantId);

    public boolean disapproveRestaurant(String restaurantId);
}
