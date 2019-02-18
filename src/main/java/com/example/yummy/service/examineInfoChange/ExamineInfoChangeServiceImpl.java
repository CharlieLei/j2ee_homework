package com.example.yummy.service.examineInfoChange;

import com.example.yummy.dao.RestaurantDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.restaurant.Restaurant;

import java.util.List;

public class ExamineInfoChangeServiceImpl implements ExamineInfoChangeService {

    private RestaurantDao restaurantDao = DaoFactory.getRestaurantDao();

    @Override
    public List<Restaurant> getAllRestaurantInfoChanges() {
        return restaurantDao.getAllInfoChanges();
    }

    @Override
    public boolean approveRestaurantInfoChange(String restaurantId) {
        return restaurantDao.setInfoChangeSettled(restaurantId);
    }
}
