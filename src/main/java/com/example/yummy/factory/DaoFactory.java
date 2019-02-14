package com.example.yummy.factory;


import com.example.yummy.dao.*;

public class DaoFactory {
    public static MemberDao getMemberDao() {
        return new MemberDaoImpl();
    }

    public static RestaurantDao getRestaurantDao() {
        return new RestaurantDaoImpl();
    }

    public static ManagerDao getManagerDao() {
        return null;
    }
}
