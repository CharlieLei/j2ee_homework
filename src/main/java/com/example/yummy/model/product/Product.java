package com.example.yummy.model.product;

/**
 * 餐厅提供的产品
 */
public abstract class Product {
    protected long id;
    protected long restaurantId;

    private String name;
    private double price;
    private int quantity;
}
