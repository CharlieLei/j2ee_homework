package com.example.yummy.service.restaurant;

import com.example.yummy.model.Address;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantInfo;
import com.example.yummy.model.restaurant.RestaurantType;

import java.util.List;

public interface RestaurantAccountService {
    public String register(String password, RestaurantInfo restaurantInfo);

    public boolean login(String restaurantId, String password);

    public boolean modifyInfo(String restaurantId, RestaurantInfo restaurantInfo);

    public boolean modifyAddress(String restaurantId, Address address);
}
