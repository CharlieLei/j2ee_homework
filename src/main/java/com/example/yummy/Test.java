package com.example.yummy;


import com.example.yummy.model.Address;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.member.MemberDeliveryAddress;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderItem;
import com.example.yummy.model.order.OrderState;
import com.example.yummy.model.product.Product;
import com.example.yummy.model.product.ProductItem;
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
        OrderService service = new OrderServiceImpl();

        Order order = new Order();
        order.setMemberId("123456");
        order.setRestaurantId("abc123");

        Address senderAddr = new Address();
        senderAddr.setLongitude(98);
        senderAddr.setLatitude(132);
        senderAddr.setAddrName("sender");

        Address receiverAddr = new Address();
        receiverAddr.setLongitude(-123);
        receiverAddr.setLatitude(83);
        receiverAddr.setAddrName("receiver");

        order.setSenderAddr(senderAddr);
        order.setReceiverAddr(receiverAddr);

        List<OrderItem> list = new ArrayList<>();
        OrderItem item = new OrderItem();
        item.setProductId(2);
        list.add(item);

        item = new OrderItem();
        item.setProductId(3);
        list.add(item);

        order.setOrderItemList(list);
        order.setState(OrderState.PAYING);

        service.place(order);

        while (true) {

        }

//        ProductService service = new ProductServiceImpl();
//
//        Product product = new Product();
//        List<ProductItem> list = new ArrayList<>();
//        ProductItem item = new ProductItem();
//        item.setItemId(3);
//        list.add(item);
//
//        item = new ProductItem();
//        item.setItemId(5);
//        list.add(item);
//        product.setProductItemIdList(list);
//
//        service.add(product);

//        MemberAccountService service = new MemberAccountServiceImpl();
//
//        Member member = new Member();
//        member.setId("123456");
//        member.setEmail("leicheng2008@hotmail.com");
//
//        List<MemberDeliveryAddress> list = new ArrayList<>();
//        MemberDeliveryAddress address = new MemberDeliveryAddress();
//
//        address.setMemberId("123456");
//        address.setAddrName("addr");
//        address.setLongitude(-98);
//        address.setLatitude(86);
//
//        list.add(address);
//        member.setDeliveryAddrList(list);
//
//        service.register(member);
    }
}
