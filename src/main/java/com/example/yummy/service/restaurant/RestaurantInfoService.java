package com.example.yummy.service.restaurant;

import com.example.yummy.model.Address;
import com.example.yummy.model.restaurant.RestaurantType;

public interface RestaurantInfoService {
    /**
     * 修改餐厅地点
     * @param restaurantId 餐厅ID
     * @param newAddr 新的餐厅地点
     * @return
     */
    public boolean changeAddr(long restaurantId, Address newAddr);

    /**
     * 修改餐厅类型
     * @param restaurantId 餐厅ID
     * @param newType 新的餐厅类型
     * @return
     */
    public boolean changeRestaurantType(long restaurantId, RestaurantType newType);
}
