package com.example.yummy.service.restaurant;

import com.example.yummy.model.product.Combo;
import com.example.yummy.model.product.FoodItem;

import java.sql.Timestamp;

/**
 * 餐厅处理所销售的食品信息
 */
public interface MealService {
    public boolean addFoodItem(long restaurantId, FoodItem foodItem, Timestamp startTime, Timestamp endTime);

    public boolean removeFoodItem(long restaurantId, long productId);

    public boolean addCombo(long restaurantId, Combo combo, Timestamp startTime, Timestamp endTime);

    public boolean removeCombo(long restaurantId, long productId);
}
