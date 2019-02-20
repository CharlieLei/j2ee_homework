package com.example.yummy.service.restaurant;

import com.example.yummy.dao.RestaurantDao;
import com.example.yummy.dao.RestaurantInfoChangeDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.Address;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantInfo;
import com.example.yummy.model.restaurant.RestaurantInfoChange;
import com.example.yummy.util.StringUtil;

public class RestaurantAccountServiceImpl implements RestaurantAccountService {

    private RestaurantDao restaurantDao = DaoFactory.getRestaurantDao();
    private RestaurantInfoChangeDao restaurantInfoChangeDao = DaoFactory.getRestaurantInfoChangeDao();

    @Override
    public String register(String password, RestaurantInfo restaurantInfo) {
        Restaurant restaurant = new Restaurant();
        restaurant.setPassword(password);
        restaurant.setRestaurantInfo(restaurantInfo);
        restaurant.setBalance(0);

        String restaurantId = StringUtil.generateRestaurantId();
        restaurant.setId(restaurantId);

        restaurantDao.add(restaurant);

        return restaurant.getId();
    }

    @Override
    public boolean login(String restaurantId, String password) {
        return restaurantDao.isLoginInfoCorrect(restaurantId, password);
    }

    @Override
    public boolean modifyInfo(String restaurantId, RestaurantInfo restaurantInfo) {
        RestaurantInfoChange restaurantInfoChange = restaurantInfoChangeDao.getUnexamined(restaurantId);

        if (restaurantInfoChange == null) {
            restaurantInfoChange = new RestaurantInfoChange();
            restaurantInfoChange.setRestaurantId(restaurantId);
            restaurantInfoChange.setRestaurantInfo(restaurantInfo);
            restaurantInfoChange.setExamined(false);
            restaurantInfoChange.setApproved(false);
            return restaurantInfoChangeDao.add(restaurantInfoChange);
        }else {
            restaurantInfoChange.setRestaurantInfo(restaurantInfo);
            return restaurantInfoChangeDao.modify(restaurantInfoChange);
        }
    }

    @Override
    public boolean modifyAddress(String restaurantId, Address address) {
        RestaurantInfoChange restaurantInfoChange = restaurantInfoChangeDao.getUnexamined(restaurantId);

        if (restaurantInfoChange == null) {
            restaurantInfoChange = new RestaurantInfoChange();
            restaurantInfoChange.setRestaurantId(restaurantId);
            restaurantInfoChange.setAddress(address);
            restaurantInfoChange.setExamined(false);
            restaurantInfoChange.setApproved(false);
            return restaurantInfoChangeDao.add(restaurantInfoChange);
        }else {
            restaurantInfoChange.setAddress(address);
            return restaurantInfoChangeDao.modify(restaurantInfoChange);
        }
    }
}
