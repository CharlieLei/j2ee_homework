package com.example.yummy.service.restaurant;

public interface RestaurantAccountService {
    /**
     * 餐厅注册
     * @param password 密码
     * @return 餐厅的7位识别码
     */
    public long register(String password);

    public boolean login(long restaurantId, String password);
}
