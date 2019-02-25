package com.example.yummy.service.food;

import com.example.yummy.model.product.FoodItem;

import java.sql.Timestamp;
import java.util.List;

public interface FoodItemService {
    public boolean add(FoodItem foodItem);

    public boolean modify(FoodItem foodItem);

    public FoodItem get(int itemId);

    public List<FoodItem> getAllByRestaurant(String restaurantId);

    public List<FoodItem> getAllByRestaurant(String restaurantId, Timestamp startTime, Timestamp endTime);
}
