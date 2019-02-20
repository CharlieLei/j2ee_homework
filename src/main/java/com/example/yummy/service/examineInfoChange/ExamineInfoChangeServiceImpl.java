package com.example.yummy.service.examineInfoChange;

import com.example.yummy.dao.RestaurantDao;
import com.example.yummy.dao.RestaurantInfoChangeDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantInfoChange;

import java.util.List;

public class ExamineInfoChangeServiceImpl implements ExamineInfoChangeService {

    private RestaurantInfoChangeDao restaurantInfoChangeDao = DaoFactory.getRestaurantInfoChangeDao();

    @Override
    public List<RestaurantInfoChange> getAllUnexaminedRestaurantInfoChanges() {
        return restaurantInfoChangeDao.getAllUnexaminedInfoChanges();
    }

    @Override
    public boolean approveRestaurantInfoChange(String restaurantId) {
        RestaurantInfoChange restaurantInfoChange = restaurantInfoChangeDao.getUnexamined(restaurantId);
        if (restaurantInfoChange != null) {
            restaurantInfoChange.setApproved(true);
            return restaurantInfoChangeDao.modify(restaurantInfoChange);
        }else {
            return false;
        }
    }
}
