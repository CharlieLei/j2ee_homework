package com.example.yummy.dao;

import com.example.yummy.model.product.Product;

import java.util.List;

public interface ProductDao {
    public boolean add(Product product);

    public boolean delete(int productId);

    public boolean modify(Product product);

    public Product get(int productId);

    public List<Product> getAllByRestaurant(String restaurantId);
}
