package com.example.yummy.service.restaurant;

import com.example.yummy.dao.restaurant.RestaurantDao;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantDao restaurantDao;

    @Override
    public Restaurant get(String restaurantId) {
        return restaurantDao.get(restaurantId);
    }

    @Override
    public List<Restaurant> getRestaurantsByType(RestaurantType restaurantType) {
        return restaurantDao.getRestaurantsByType(restaurantType);
    }
}
