package com.example.yummy.service.examineInfoChange;

import com.example.yummy.dao.restaurant.RestaurantDao;
import com.example.yummy.dao.restaurant.RestaurantInfoChangeDao;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantInfoChange;
import com.example.yummy.model.restaurant.RestaurantState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamineInfoChangeServiceImpl implements ExamineInfoChangeService {

    @Autowired
    private RestaurantInfoChangeDao restaurantInfoChangeDao;
    @Autowired
    private RestaurantDao restaurantDao;

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

    @Override
    public List<Restaurant> getAllUnactivatedRestaurants() {
        return restaurantDao.getRestaurantsByType(null, RestaurantState.NOT_ACTIVATED);
    }

    @Override
    public boolean approveRestaurant(String restaurantId) {
        Restaurant restaurant = restaurantDao.get(restaurantId);
        restaurant.setState(RestaurantState.ACTIVATED);
        return restaurantDao.modify(restaurant);
    }

    @Override
    public boolean disapproveRestaurant(String restaurantId) {
        Restaurant restaurant = restaurantDao.get(restaurantId);
        restaurant.setState(RestaurantState.DISAPPROVED);
        return restaurantDao.modify(restaurant);
    }
}
