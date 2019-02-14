package com.example.yummy.model.order;

import com.example.yummy.model.Address;

import java.sql.Timestamp;

/**
 * 订单
 */
public class Order {
    private long id;

    private String memberId;
    private long restaurantId;

    private Address memberDeliveryAddr;

    private Timestamp placingOrderTime;//下订单的时间
    private Timestamp fulfillingOrderTime;//完成订单的时间

    private double totalAmount;//总金额
    private OrderState state;
}
