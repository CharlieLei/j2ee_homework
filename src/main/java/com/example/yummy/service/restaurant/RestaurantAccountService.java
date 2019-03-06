package com.example.yummy.service.restaurant;

import com.example.yummy.model.Address;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantInfo;
import com.example.yummy.model.restaurant.RestaurantType;

import java.util.List;

public interface RestaurantAccountService {
    public boolean register(Restaurant restaurant);

    public boolean login(String restaurantId, String password);

    public boolean modifyInfo(String restaurantId, RestaurantInfo restaurantInfo);

    public boolean modifyAddress(String restaurantId, Address address);
}
