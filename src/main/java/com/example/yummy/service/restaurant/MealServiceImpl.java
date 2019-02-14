package com.example.yummy.service.restaurant;

import com.example.yummy.model.product.Combo;
import com.example.yummy.model.product.FoodItem;

import java.sql.Timestamp;

public class MealServiceImpl implements MealService {
    @Override
    public boolean addFoodItem(long restaurantId, FoodItem foodItem, Timestamp startTime, Timestamp endTime) {
        return false;
    }

    @Override
    public boolean removeFoodItem(long restaurantId, long productId) {
        return false;
    }

    @Override
    public boolean addCombo(long restaurantId, Combo combo, Timestamp startTime, Timestamp endTime) {
        return false;
    }

    @Override
    public boolean removeCombo(long restaurantId, long productId) {
        return false;
    }
}
