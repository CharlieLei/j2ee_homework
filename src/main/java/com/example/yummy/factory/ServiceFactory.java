package com.example.yummy.factory;

import com.example.yummy.controller.ExamineInfoChangeController;
import com.example.yummy.service.examineInfoChange.ExamineInfoChangeService;
import com.example.yummy.service.examineInfoChange.ExamineInfoChangeServiceImpl;
import com.example.yummy.service.manager.ManagerAccountService;
import com.example.yummy.service.manager.ManagerAccountServiceImpl;
import com.example.yummy.service.manager.ManagerStatisticsService;
import com.example.yummy.service.manager.ManagerStatisticsServiceImpl;
import com.example.yummy.service.member.*;
import com.example.yummy.service.order.OrderService;
import com.example.yummy.service.order.OrderServiceImpl;
import com.example.yummy.service.product.ProductService;
import com.example.yummy.service.product.ProductServiceImpl;
import com.example.yummy.service.restaurant.*;

public class ServiceFactory {
    public static MemberAccountService getMemberAccountService() {
        return new MemberAccountServiceImpl();
    }

    public static MemberService getMemberService() {
        return new MemberServiceImpl();
    }

    public static MemberStatisticsService getMemberStatisticsService() {
        return new MemberStatisticsServiceImpl();
    }

    public static RestaurantAccountService getRestaurantAccountService() {
        return new RestaurantAccountServiceImpl();
    }

    public static RestaurantService getRestaurantService() {
        return new RestaurantServiceImpl();
    }

    public static RestaurantStatisticsService getRestaurantStatisticsService() {
        return new RestaurantStatisticsServiceImpl();
    }

    public static ManagerAccountService getManagerAccountService() {
        return new ManagerAccountServiceImpl();
    }

    public static ManagerStatisticsService getManagerStatisticsService() {
        return new ManagerStatisticsServiceImpl();
    }

    public static OrderService getOrderService() {
        return new OrderServiceImpl();
    }

    public static ProductService getProductService() {
        return new ProductServiceImpl();
    }

    public static ExamineInfoChangeService getExamineInfoChangeService() {
        return new ExamineInfoChangeServiceImpl();
    }
}
