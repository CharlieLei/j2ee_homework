package com.example.yummy.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "foodCombinations")
public class FoodCombination implements Serializable {
    @Id
    @Column(name = "combinationId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "restaurantId")
    private String restaurantId;

    @Column(name = "combinationName")
    private String name;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "originalTotalPrice")
    private double originalTotalPrice;
    @Column(name = "discount")
    private double discount;
    @Column(name = "totalPrice")
    private double totalPrice;
    @Column(name = "startTime")
    private Timestamp startTime;
    @Column(name = "endTime")
    private Timestamp endTime;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "combinationId", referencedColumnName = "combinationId")
    private List<CombinationItem> combinationItemList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        for (CombinationItem item: combinationItemList) {
            item.setCombinationId(this.id);
        }
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<CombinationItem> getCombinationItemList() {
        return combinationItemList;
    }

    public void setCombinationItemList(List<CombinationItem> combinationItemList) {
        this.combinationItemList = combinationItemList;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public double getOriginalTotalPrice() {
        return originalTotalPrice;
    }

    public void setOriginalTotalPrice(double originalTotalPrice) {
        this.originalTotalPrice = originalTotalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
