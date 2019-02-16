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
//    private Address addr;
    @Column(name = "restaurantType")
    private RestaurantType type;

    public Restaurant() {
    }

    public Restaurant(String password, String name) {
        this.password = password;
        this.name = name;
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
}
