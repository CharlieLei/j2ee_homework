package com.example.yummy.dao;

import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantType;

import java.util.List;

public interface RestaurantDao {
    public boolean isLoginInfoCorrect(String restaurantId, String password);

    public boolean add(Restaurant restaurant);

    public boolean modify(Restaurant restaurant);

    public Restaurant get(String restaurantId);

    public List<Restaurant> getAllInfoChanges();

    public boolean setInfoChangeSettled(String restaurantId);

    public List<Restaurant> getRestaurantByType(RestaurantType type);
}
