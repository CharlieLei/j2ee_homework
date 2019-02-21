package com.example.yummy.dao;

import com.example.yummy.model.restaurant.RestaurantInfoChange;

import java.util.List;

public interface RestaurantInfoChangeDao {
    public boolean add(RestaurantInfoChange restaurantInfoChange);

    public boolean modify(RestaurantInfoChange restaurantInfoChange);

    public RestaurantInfoChange get(int id);

    public RestaurantInfoChange getUnexamined(String restaurantId);

    public List<RestaurantInfoChange> getAllUnexaminedInfoChanges();
}
