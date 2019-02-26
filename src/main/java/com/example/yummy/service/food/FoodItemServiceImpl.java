package com.example.yummy.service.food;

import com.example.yummy.dao.food.FoodItemDao;
import com.example.yummy.model.product.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    @Autowired
    private FoodItemDao foodItemDao;

    @Override
    public boolean add(FoodItem foodItem) {
        return foodItemDao.add(foodItem);
    }

    @Override
    public boolean modify(FoodItem foodItem) {
        return foodItemDao.modify(foodItem);
    }

    @Override
    public FoodItem get(int itemId) {
        return foodItemDao.get(itemId);
    }

    @Override
    public List<FoodItem> getAllByRestaurant(String restaurantId) {
        return foodItemDao.getAllByRestaurant(restaurantId);
    }

    @Override
    public List<FoodItem> getAllByRestaurant(String restaurantId, Timestamp startTime, Timestamp endTime) {
        return foodItemDao.getAllByRestaurant(restaurantId, startTime, endTime);
    }
}
