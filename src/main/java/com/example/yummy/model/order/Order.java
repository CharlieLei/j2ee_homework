package com.example.yummy.model.order;

import com.example.yummy.model.Address;
import com.example.yummy.model.product.Product;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * 订单
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "orderId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "memberId")
    private String memberId;
    @Column(name = "restaurantId")
    private String restaurantId;

    @Column(name = "placingOrderTime")
    private Timestamp placingOrderTime;//下订单的时间
    @Column(name = "fulfillingOrderTime")
    private Timestamp fulfillingOrderTime;//完成订单的时间

    private Address senderAddr;
    private Address receiverAddr;

//    private List<Product> productList;

    @Column(name = "totalAmount")
    private double totalAmount;//总金额
    @Column(name = "refund")
    private double refund;
    @Column(name = "state")
    private OrderState state;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
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

    public Address getSenderAddr() {
        return senderAddr;
    }

    public void setSenderAddr(Address senderAddr) {
        this.senderAddr = senderAddr;
    }

    public Address getReceiverAddr() {
        return receiverAddr;
    }

    public void setReceiverAddr(Address receiverAddr) {
        this.receiverAddr = receiverAddr;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getRefund() {
        return refund;
    }

    public void setRefund(double refund) {
        this.refund = refund;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}
