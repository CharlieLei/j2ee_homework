package com.example.yummy.dao;

import com.example.yummy.model.restaurant.Restaurant;

public interface RestaurantDao {
    public boolean isLoginInfoCorrect(String restaurantId, String password);

    public boolean add(Restaurant restaurant);

    public boolean modify(Restaurant restaurant);

    public Restaurant get(String restaurantId);
}
