package com.example.yummy.dao;

public interface RestaurantDao {
    public boolean isLoginInfoCorrect(long restaurantId, String password);
}
