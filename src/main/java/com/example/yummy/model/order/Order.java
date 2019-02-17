package com.example.yummy.model.order;

import com.example.yummy.model.Address;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 订单
 */
@Entity
@Table(name = "orders")
public class Order {
    private long id;

    private String memberId;
    private long restaurantId;

    private Address memberDeliveryAddr;

    private Timestamp placingOrderTime;//下订单的时间
    private Timestamp fulfillingOrderTime;//完成订单的时间

    private double totalAmount;//总金额
    private OrderState state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Address getMemberDeliveryAddr() {
        return memberDeliveryAddr;
    }

    public void setMemberDeliveryAddr(Address memberDeliveryAddr) {
        this.memberDeliveryAddr = memberDeliveryAddr;
    }

    public Timestamp getPlacingOrderTime() {
        return placingOrderTime;
    }

    public void setPlacingOrderTime(Timestamp placingOrderTime) {
        this.placingOrderTime = placingOrderTime;
    }

    public Timestamp getFulfillingOrderTime() {
        return fulfillingOrderTime;
    }

    public void setFulfillingOrderTime(Timestamp fulfillingOrderTime) {
        this.fulfillingOrderTime = fulfillingOrderTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}
