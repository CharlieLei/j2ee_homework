package com.example.yummy.model.order;

import com.example.yummy.model.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 订单
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {
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
    @Column(name = "payDeadline")
    private Timestamp payDeadline;//付款时间
    @Column(name = "fulfillingOrderTime")
    private Timestamp fulfillingOrderTime;//完成订单的时间

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "longitude", column = @Column(name = "senderLongitude")),
            @AttributeOverride(name = "latitude", column = @Column(name = "senderLatitude")),
            @AttributeOverride(name = "addrName", column = @Column(name = "senderAddrName"))
    })
    private Address senderAddr;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "longitude", column = @Column(name = "receiverLongitude")),
            @AttributeOverride(name = "latitude", column = @Column(name = "receiverLatitude")),
            @AttributeOverride(name = "addrName", column = @Column(name = "receiverAddrName"))
    })
    private Address receiverAddr;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private List<OrderFoodItem> orderFoodItemList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private List<OrderFoodCombination> orderFoodCombinationList;

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
        if (orderFoodItemList != null) {
            for (OrderFoodItem item: orderFoodItemList) {
                item.setOrderId(this.id);
            }
        }
        if (orderFoodCombinationList != null) {
            for (OrderFoodCombination item: orderFoodCombinationList) {
                item.setOrderId(this.id);
            }
        }
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

    public Timestamp getPayDeadline() {
        return payDeadline;
    }

    public void setPayDeadline(Timestamp payDeadline) {
        this.payDeadline = payDeadline;
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

    public List<OrderFoodItem> getOrderFoodItemList() {
        return orderFoodItemList;
    }

    public void setOrderFoodItemList(List<OrderFoodItem> orderFoodItemList) {
        this.orderFoodItemList = orderFoodItemList;
    }

    public List<OrderFoodCombination> getOrderFoodCombinationList() {
        return orderFoodCombinationList;
    }

    public void setOrderFoodCombinationList(List<OrderFoodCombination> orderFoodCombinationList) {
        this.orderFoodCombinationList = orderFoodCombinationList;
    }
}
