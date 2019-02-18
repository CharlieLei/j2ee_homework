package com.example.yummy.service.restaurant;

import com.example.yummy.dao.RestaurantDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantType;

import java.util.List;

public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantDao restaurantDao = DaoFactory.getRestaurantDao();

    @Override
    public Restaurant get(String restaurantId) {
        return restaurantDao.get(restaurantId);
    }

    @Override
    public List<Restaurant> getRestaurantsByType(RestaurantType restaurantType) {
        return restaurantDao.getRestaurantsByType(restaurantType);
    }
}
