package com.example.yummy.model.restaurant;

import com.example.yummy.model.Address;

import javax.persistence.*;

@Entity
@Table(name = "restaurantInfoChange")
public class RestaurantInfoChange {
    @Id
    @Column(name = "restaurantId")
    private String id;
    @Column(name = "restaurantName")
    private String name;
    @Embedded
    private Address address;
    @Column(name = "restaurantType")
    private RestaurantType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public RestaurantType getType() {
        return type;
    }

    public void setType(RestaurantType type) {
        this.type = type;
    }
}
