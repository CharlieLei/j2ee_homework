package com.example.yummy.model.order;

import javax.persistence.*;

@Entity
@Table(name = "orderItems")
public class OrderItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "orderId")
    private int orderId;
    @Column(name = "productId")
    private int productId;
    @Column(name = "itemAmount")
    private int itemAmount;
    @Column(name = "itemPrice")
    private double itemPrice;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
