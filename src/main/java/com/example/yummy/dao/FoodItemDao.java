package com.example.yummy.dao;

import com.example.yummy.model.product.FoodItem;

import java.sql.Timestamp;
import java.util.List;

public interface FoodItemDao {
    public boolean add(FoodItem foodItem);

    public boolean delete(int itemId);

    public boolean modify(FoodItem foodItem);

    public FoodItem get(int itemId);

    public List<FoodItem> getAllByRestaurant(String restaurantId);

    public List<FoodItem> getAllByRestaurant(String restaurantId, Timestamp startTime, Timestamp endTime);
}
