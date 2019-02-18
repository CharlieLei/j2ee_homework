package com.example.yummy.service.restaurant;

import com.example.yummy.dao.RestaurantDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.util.StringUtil;

public class RestaurantAccountServiceImpl implements RestaurantAccountService {

    private RestaurantDao restaurantDao = DaoFactory.getRestaurantDao();

    @Override
    public String register(Restaurant restaurant) {
        String memberId = StringUtil.generateMemberId();
        restaurant.setId(memberId);

        restaurantDao.add(restaurant);

        return memberId;
    }

    @Override
    public boolean login(String restaurantId, String password) {
        return restaurantDao.isLoginInfoCorrect(restaurantId, password);
    }

    @Override
    public boolean modifyInfo(Restaurant restaurant) {
        return restaurantDao.modify(restaurant);
    }
}
