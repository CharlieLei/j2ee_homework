package com.example.yummy.service.restaurant;

import com.example.yummy.dao.restaurant.RestaurantDao;
import com.example.yummy.dao.restaurant.RestaurantInfoChangeDao;
import com.example.yummy.model.Address;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantInfo;
import com.example.yummy.model.restaurant.RestaurantInfoChange;
import com.example.yummy.model.restaurant.RestaurantState;
import com.example.yummy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantAccountServiceImpl implements RestaurantAccountService {

    @Autowired
    private RestaurantDao restaurantDao;
    @Autowired
    private RestaurantInfoChangeDao restaurantInfoChangeDao;

    @Override
    public boolean register(Restaurant restaurant) {

        restaurant.setBalance(0);
        restaurant.setState(RestaurantState.NOT_ACTIVATED);

        return restaurantDao.add(restaurant);
    }

    @Override
    public boolean login(String restaurantId, String password) {
        return restaurantDao.isLoginInfoCorrect(restaurantId, password);
    }

    @Override
    public boolean modifyInfo(String restaurantId, RestaurantInfo restaurantInfo) {

        Restaurant restaurant = restaurantDao.get(restaurantId);
        restaurant.setState(RestaurantState.MODIFYING);
        restaurantDao.modify(restaurant);

        RestaurantInfoChange restaurantInfoChange = restaurantInfoChangeDao.getUnexamined(restaurantId);

        if (restaurantInfoChange == null) {
            restaurantInfoChange = new RestaurantInfoChange();
            restaurantInfoChange.setRestaurantId(restaurantId);
            restaurantInfoChange.setRestaurantInfo(restaurantInfo);
            restaurantInfoChange.setExamined(false);
            restaurantInfoChange.setApproved(false);
            return restaurantInfoChangeDao.add(restaurantInfoChange);
        }else {
            restaurantInfoChange.setRestaurantInfo(restaurantInfo);
            return restaurantInfoChangeDao.modify(restaurantInfoChange);
        }
    }

    @Override
    public boolean modifyAddress(String restaurantId, Address address) {
        RestaurantInfoChange restaurantInfoChange = restaurantInfoChangeDao.getUnexamined(restaurantId);

        if (restaurantInfoChange == null) {
            restaurantInfoChange = new RestaurantInfoChange();
            restaurantInfoChange.setRestaurantId(restaurantId);
            restaurantInfoChange.setAddress(address);
            restaurantInfoChange.setExamined(false);
            restaurantInfoChange.setApproved(false);
            return restaurantInfoChangeDao.add(restaurantInfoChange);
        }else {
            restaurantInfoChange.setAddress(address);
            return restaurantInfoChangeDao.modify(restaurantInfoChange);
        }
    }
}
