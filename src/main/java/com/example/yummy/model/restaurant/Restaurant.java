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

    @Column(name = "restaurantName")
    private String name;
    @Embedded
    private Address address;
    @Column(name = "balance")
    private double balance;
    @Column(name = "restaurantType")
    private RestaurantType type;

    public Restaurant() {
    }

    public Restaurant(String password, String name, RestaurantType type) {
        this.password = password;
        this.name = name;
        this.type = type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RestaurantType getType() {
        return type;
    }

    public void setType(RestaurantType type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
