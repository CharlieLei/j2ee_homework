package com.example.yummy.model.coupon;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "coupons")
public class Coupon implements Serializable {
    @Id
    @Column(name = "couponId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "restaurantId")
    private String restaurantId;
    @Column(name = "value")
    private double value;
    @Column(name = "amount")
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
}
