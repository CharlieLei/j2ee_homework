package com.example.yummy.factory;


import com.example.yummy.dao.*;
import com.example.yummy.dao.impl.*;

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
        return new ManagerDaoImpl();
    }

    public static OrderDao getOrderDao() {
        return new OrderDaoImpl();
    }

    public static ProductDao getProductDao() {
        return new ProductDaoImpl();
    }

    public static YummyDao getYummyDao() {
        return new YummyDaoImpl();
    }
}
