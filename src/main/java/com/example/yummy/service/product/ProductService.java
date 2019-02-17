package com.example.yummy.service.product;

import com.example.yummy.model.product.Product;

import java.util.List;

public interface ProductService {
    public boolean add(Product product);

    public List<Product> getAllProducts(String restaurantId);
}
