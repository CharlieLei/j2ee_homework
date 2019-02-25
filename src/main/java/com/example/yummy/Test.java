package com.example.yummy;


import com.example.yummy.dao.FoodItemDao;
import com.example.yummy.dao.RestaurantDao;
import com.example.yummy.dao.impl.FoodItemDaoImpl;
import com.example.yummy.dao.impl.RestaurantDaoImpl;
import com.example.yummy.model.Address;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.member.MemberDeliveryAddress;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderItem;
import com.example.yummy.model.order.OrderState;
import com.example.yummy.model.product.FoodItem;
import com.example.yummy.model.product.Product;
import com.example.yummy.model.product.ProductItem;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.service.member.MemberAccountService;
import com.example.yummy.service.member.MemberAccountServiceImpl;
import com.example.yummy.service.order.OrderService;
import com.example.yummy.service.order.OrderServiceImpl;
import com.example.yummy.service.product.ProductService;
import com.example.yummy.service.product.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        FoodItemDao dao = new FoodItemDaoImpl();

        FoodItem foodItem = new FoodItem();
        foodItem.setRestaurantId("qwer123");
        foodItem.setName("小龙虾");
        foodItem.setQuantity(100);
        foodItem.setPrice(54.7);

        dao.add(foodItem);
    }
}
