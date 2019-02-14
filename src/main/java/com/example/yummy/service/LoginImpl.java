package com.example.yummy.service;

import com.example.yummy.dao.ManagerDao;
import com.example.yummy.dao.MemberDao;
import com.example.yummy.dao.RestaurantDao;
import com.example.yummy.factory.DaoFactory;

public class LoginImpl implements LoginService {
    @Override
    public boolean memberLogin(String memberId, String password) {
        MemberDao memberDao = DaoFactory.getMemberDao();
        return memberDao.isLoginInfoCorrect(memberId, password);
    }

    @Override
    public boolean restaurantLogin(long restaurantId, String password) {
        RestaurantDao restaurantDao = DaoFactory.getRestaurantDao();
        return restaurantDao.isLoginInfoCorrect(restaurantId, password);
    }

    @Override
    public boolean managerLogin(String managerId, String password) {
        ManagerDao managerDao = DaoFactory.getManagerDao();
        return managerDao.isLoginInfoCorrect(managerId, password);
    }
}
