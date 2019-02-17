package com.example.yummy.model.product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 餐厅提供的产品
 */
@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @Column(name = "productId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(name = "restaurantId")
    protected String restaurantId;

    @Column(name = "productName")
    private String name;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private double price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId")
    private List<ProductItem> productItemIdList;


    public Product() {
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<ProductItem> getProductItemIdList() {
        return productItemIdList;
    }

    public void setProductItemIdList(List<ProductItem> productItemIdList) {
        this.productItemIdList = productItemIdList;
    }
}
