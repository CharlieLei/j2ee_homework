package com.example.yummy.service.restaurant;

import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantType;

import java.util.List;

public interface RestaurantAccountService {
    /**
     * 餐厅注册
     * @param restaurant 餐厅
     * @return 餐厅的7位识别码
     */
    public String register(Restaurant restaurant);

    public boolean login(String restaurantId, String password);

    public boolean modifyInfo(Restaurant restaurant);
}
