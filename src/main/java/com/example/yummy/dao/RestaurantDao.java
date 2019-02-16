package com.example.yummy.dao;

import com.example.yummy.model.restaurant.Restaurant;

public interface RestaurantDao {
    public boolean isLoginInfoCorrect(String restaurantId, String password);

    public void add(Restaurant restaurant);

    public Restaurant get(String restaurantId);
}
