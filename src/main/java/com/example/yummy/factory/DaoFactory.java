package com.example.yummy.factory;


import com.example.yummy.dao.*;
import com.example.yummy.dao.impl.BaseDaoImpl;
import com.example.yummy.dao.impl.MemberDaoImpl;
import com.example.yummy.dao.impl.RestaurantDaoImpl;

public class DaoFactory {
    public static BaseDao getBaseDao() {
        return new BaseDaoImpl();
    }

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
