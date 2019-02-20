package com.example.yummy.model.restaurant;


import javax.persistence.*;

@Embeddable
public class RestaurantInfo {
    @Column(name = "restaurantName")
    private String name;
    @Column(name = "restaurantType")
    @Enumerated(EnumType.ORDINAL)
    private RestaurantType restaurantType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RestaurantType getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(RestaurantType restaurantType) {
        this.restaurantType = restaurantType;
    }
}
