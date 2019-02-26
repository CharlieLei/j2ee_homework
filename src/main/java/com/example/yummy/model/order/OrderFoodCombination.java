package com.example.yummy.model.order;

import com.example.yummy.model.product.FoodCombination;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orderFoodCombinations")
public class OrderFoodCombination implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "orderId")
    private int orderId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "combinationId", referencedColumnName = "combinationId")
    private FoodCombination foodCombination;
    @Column(name = "amount")
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public FoodCombination getFoodCombination() {
        return foodCombination;
    }

    public void setFoodCombination(FoodCombination foodCombination) {
        this.foodCombination = foodCombination;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
