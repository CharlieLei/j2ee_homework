package com.example.yummy.model.restaurant;

public enum  RestaurantType {
    FAST_FOOD,  //快餐店
    CHINESE,    //中餐厅
    WESTERN;    //西餐厅

    public static RestaurantType getEnumByInt(int num) {
        for (RestaurantType type: RestaurantType.values()) {
            if (type.ordinal() == num) {
                return type;
            }
        }
        return null;
    }
}
