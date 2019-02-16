package com.example.yummy.service.restaurant;

import com.example.yummy.dao.RestaurantDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.util.StringUtil;

public class RestaurantAccountImpl implements RestaurantAccountService {

    @Override
    public String register(Restaurant restaurant) {
        String memberId = StringUtil.generateMemberId();
        restaurant.setId(memberId);

        RestaurantDao restaurantDao = DaoFactory.getRestaurantDao();
        restaurantDao.add(restaurant);

        return memberId;
    }

    @Override
    public boolean login(String restaurantId, String password) {
        RestaurantDao restaurantDao = DaoFactory.getRestaurantDao();
        return restaurantDao.isLoginInfoCorrect(restaurantId, password);
    }
}
