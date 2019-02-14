package com.example.yummy.service;

public interface LoginService {
    public boolean memberLogin(String memberId, String password);

    public boolean restaurantLogin(long restaurantId, String password);

    public boolean managerLogin(String managerId, String password);
}
