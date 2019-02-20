package com.example.yummy.model.restaurant;

import com.example.yummy.model.Address;

import javax.persistence.*;

@Entity
@Table(name = "restaurantInfoChanges")
public class RestaurantInfoChange {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "restaurantId")
    private String restaurantId;
    @Embedded
    private RestaurantInfo restaurantInfo;
    @Embedded
    private Address address;
    @Column(name = "isExamined")
    private boolean isExamined;
    @Column(name = "isApproved")
    private boolean isApproved;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public RestaurantInfo getRestaurantInfo() {
        return restaurantInfo;
    }

    public void setRestaurantInfo(RestaurantInfo restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isExamined() {
        return isExamined;
    }

    public void setExamined(boolean examined) {
        isExamined = examined;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
