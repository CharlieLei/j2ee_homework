package com.example.yummy.service.restaurant;

import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantType;

import java.util.List;

public interface RestaurantService {
    public Restaurant get(String restaurantId);

    public List<Restaurant> getActivatedRestaurantsByType(RestaurantType restaurantType);
}
