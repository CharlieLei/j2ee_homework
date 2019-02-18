package com.example.yummy.service.product;

import com.example.yummy.dao.ProductDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.product.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = DaoFactory.getProductDao();

    @Override
    public boolean add(Product product) {
        return productDao.add(product);
    }

    @Override
    public List<Product> getAllProducts(String restaurantId) {
        return productDao.getAllByRestaurant(restaurantId);
    }
}
