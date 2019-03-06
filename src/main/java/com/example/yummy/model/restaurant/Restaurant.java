package com.example.yummy.model.restaurant;

import com.example.yummy.model.Address;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "restaurants")
public class Restaurant implements Serializable {
    @Id
    @Column(name = "restaurantId")
    private String id;
    @Column(name = "password")
    private String password;

    @Embedded
    private RestaurantInfo restaurantInfo;
    @Column(name = "balance")
    private double balance;

    @Embedded
    private Address address;

    @Column(name = "state")
    private RestaurantState state;

    public Restaurant() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public RestaurantInfo getRestaurantInfo() {
        return restaurantInfo;
    }

    public void setRestaurantInfo(RestaurantInfo restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public RestaurantState getState() {
        return state;
    }

    public void setState(RestaurantState state) {
        this.state = state;
    }
}
