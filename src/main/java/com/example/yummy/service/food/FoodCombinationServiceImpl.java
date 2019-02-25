package com.example.yummy.service.food;

import com.example.yummy.dao.FoodCombinationDao;
import com.example.yummy.dao.FoodItemDao;
import com.example.yummy.model.product.CombinationItem;
import com.example.yummy.model.product.FoodCombination;
import com.example.yummy.model.product.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FoodCombinationServiceImpl implements FoodCombinationService {

    @Autowired
    private FoodCombinationDao foodCombinationDao;
    @Autowired
    private FoodItemDao foodItemDao;

    @Override
    public boolean add(FoodCombination foodCombination) {

        for (CombinationItem item: foodCombination.getCombinationItemList()) {
            FoodItem foodItem = item.getFoodItem();
            foodItem.setQuantity(foodItem.getQuantity() - item.getAmount() * foodCombination.getQuantity());
            item.setFoodItem(foodItem);
        }

        return foodCombinationDao.add(foodCombination);
    }

    @Override
    public boolean modify(FoodCombination foodCombination) {
        return foodCombinationDao.modify(foodCombination);
    }

    @Override
    public FoodCombination get(int combinationId) {
        return foodCombinationDao.get(combinationId);
    }

    @Override
    public List<FoodCombination> getAllByRestaurant(String restaurantId) {
        return foodCombinationDao.getAllByRestaurant(restaurantId);
    }

    @Override
    public List<FoodCombination> getAllByRestaurant(String restaurantId, Timestamp startTime, Timestamp endTime) {
        return foodCombinationDao.getAllByRestaurant(restaurantId, startTime, endTime);
    }
}
