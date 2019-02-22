package com.example.yummy.model.member;

import com.example.yummy.model.restaurant.RestaurantType;

public enum MemberLevel {
    GOLD,
    SILVER,
    COPPER;

    public static MemberLevel getEnum(String str) {
        for (MemberLevel type: MemberLevel.values()) {
            if (type.toString().equals(str)) {
                return type;
            }
        }
        return null;
    }
}
