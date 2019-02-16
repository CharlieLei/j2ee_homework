package com.example.yummy.service.restaurant;

import com.example.yummy.dao.RestaurantDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.Address;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantType;

public class RestaurantInfoImpl implements RestaurantInfoService {
    @Override
    public boolean changeAddr(String restaurantId, Address newAddr) {
        return false;
    }

    @Override
    public boolean changeRestaurantType(String restaurantId, RestaurantType newType) {
        return false;
    }

    @Override
    public Restaurant getRestaurant(String restaurantId) {
        RestaurantDao restaurantDao = DaoFactory.getRestaurantDao();
        return restaurantDao.get(restaurantId);
    }
}
