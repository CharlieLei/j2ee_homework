package com.example.yummy.service.food;

import com.example.yummy.model.product.FoodCombination;

import java.sql.Timestamp;
import java.util.List;

public interface FoodCombinationService {
    public boolean add(FoodCombination foodCombination);

    public boolean modify(FoodCombination foodCombination);

    public FoodCombination get(int combinationId);

    public List<FoodCombination> getAllByRestaurant(String restaurantId);

    public List<FoodCombination> getAllByRestaurant(String restaurantId, Timestamp startTime, Timestamp endTime);
}
