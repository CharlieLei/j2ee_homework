package com.example.yummy.service.examineInfoChange;

import com.example.yummy.dao.RestaurantDao;
import com.example.yummy.dao.RestaurantInfoChangeDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantInfoChange;

import java.util.List;

public class ExamineInfoChangeServiceImpl implements ExamineInfoChangeService {

    private RestaurantInfoChangeDao restaurantInfoChangeDao = DaoFactory.getRestaurantInfoChangeDao();
    private RestaurantDao restaurantDao = DaoFactory.getRestaurantDao();

    @Override
    public List<RestaurantInfoChange> getAllUnexaminedRestaurantInfoChanges() {
        return restaurantInfoChangeDao.getAllUnexaminedInfoChanges();
    }

    @Override
    public boolean approveRestaurantInfoChange(int id, String restaurantId) {
        RestaurantInfoChange restaurantInfoChange = restaurantInfoChangeDao.getUnexamined(restaurantId);
        if (restaurantInfoChange != null) {
            Restaurant restaurant = restaurantDao.get(restaurantId);

            if (restaurantInfoChange.getRestaurantInfo() != null) {
                restaurant.setRestaurantInfo(restaurantInfoChange.getRestaurantInfo());
            }
            if (restaurantInfoChange.getAddress() != null) {
                restaurant.setAddress(restaurantInfoChange.getAddress());
            }
            restaurantDao.modify(restaurant);

            restaurantInfoChange.setExamined(true);
            restaurantInfoChange.setApproved(true);
            return restaurantInfoChangeDao.modify(restaurantInfoChange);
        }else {
            return false;
        }
    }

    @Override
    public boolean disapproveRestaurantInfoChange(int id, String restaurantId) {
        RestaurantInfoChange restaurantInfoChange = restaurantInfoChangeDao.getUnexamined(restaurantId);
        if (restaurantInfoChange != null) {
            restaurantInfoChange.setExamined(true);
            restaurantInfoChange.setApproved(false);
            return restaurantInfoChangeDao.modify(restaurantInfoChange);
        }else {
            return false;
        }
    }
}
