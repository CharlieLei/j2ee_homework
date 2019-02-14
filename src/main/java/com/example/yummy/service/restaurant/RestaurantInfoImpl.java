package com.example.yummy.service.restaurant;

import com.example.yummy.model.Address;
import com.example.yummy.model.restaurant.RestaurantType;

public class RestaurantInfoImpl implements RestaurantInfoService {
    @Override
    public boolean changeAddr(long restaurantId, Address newAddr) {
        return false;
    }

    @Override
    public boolean changeRestaurantType(long restaurantId, RestaurantType newType) {
        return false;
    }
}
