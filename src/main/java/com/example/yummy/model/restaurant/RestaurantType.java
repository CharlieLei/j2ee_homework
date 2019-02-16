package com.example.yummy.model.restaurant;

public enum  RestaurantType {
    FAST_FOOD,  //快餐店
    CHINESE,    //中餐厅
    WESTERN;    //西餐厅

    public static RestaurantType getEnum(String str) {
        for (RestaurantType type: RestaurantType.values()) {
            if (type.toString().equals(str)) {
                return type;
            }
        }
        return null;
    }
}
