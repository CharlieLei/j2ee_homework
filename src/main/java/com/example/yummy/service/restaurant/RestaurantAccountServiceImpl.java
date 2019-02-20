package com.example.yummy.service.restaurant;

import com.example.yummy.dao.RestaurantDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.Address;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantInfo;
import com.example.yummy.util.StringUtil;

public class RestaurantAccountServiceImpl implements RestaurantAccountService {

    private RestaurantDao restaurantDao = DaoFactory.getRestaurantDao();

    @Override
    public String register(String password, RestaurantInfo restaurantInfo) {
        restaurantInfo.setBalance(0);

        Restaurant restaurant = new Restaurant();
        restaurant.setPassword(password);
        restaurant.setRestaurantInfo(restaurantInfo);

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
        Restaurant restaurant = restaurantDao.get(restaurantId);
        restaurant.setRestaurantInfo(restaurantInfo);
        return restaurantDao.modify(restaurant);
    }

    @Override
    public boolean modifyAddress(String restaurantId, Address address) {
        Restaurant restaurant = restaurantDao.get(restaurantId);
        restaurant.setAddress(address);
        return restaurantDao.modify(restaurant);
    }
}
