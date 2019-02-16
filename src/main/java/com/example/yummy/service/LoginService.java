package com.example.yummy.service;

public interface LoginService {
    public boolean memberLogin(String memberId, String password);

    public boolean restaurantLogin(String restaurantId, String password);

    public boolean managerLogin(String managerId, String password);
}
