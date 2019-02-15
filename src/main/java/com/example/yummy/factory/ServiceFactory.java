package com.example.yummy.factory;

import com.example.yummy.service.member.*;
import com.example.yummy.service.restaurant.RestaurantAccountImpl;
import com.example.yummy.service.restaurant.RestaurantAccountService;

public class ServiceFactory {
    public static MemberAccountService getMemberAccountService() {
        return new MemberAccountImpl();
    }

    public static MemberInfoService getMemberInfoService() {
        return new MemberInfoImpl();
    }

    public static RestaurantAccountService getRestaurantAccountService() {
        return new RestaurantAccountImpl();
    }
}
