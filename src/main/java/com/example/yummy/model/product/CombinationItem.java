package com.example.yummy.model.product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "combinationItems")
public class CombinationItem implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "combinationId")
    private int combinationId;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    private FoodItem foodItem;
    @Column(name = "amount")
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCombinationId() {
        return combinationId;
    }

    public void setCombinationId(int combinationId) {
        this.combinationId = combinationId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }
}
