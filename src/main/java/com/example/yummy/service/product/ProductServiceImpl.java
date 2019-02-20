package com.example.yummy.service.product;

import com.example.yummy.dao.ProductDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.product.Product;
import com.example.yummy.model.product.ProductItem;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = DaoFactory.getProductDao();

    @Override
    public boolean add(String restaurantId, String name, int quantity, double price, int[] itemIdList) {
        Product product = new Product();
        product.setRestaurantId(restaurantId);
        product.setName(name);
        product.setQuantity(quantity);
        product.setPrice(price);

        List<ProductItem> list = new ArrayList<>();
        for (int itemId: itemIdList) {
            ProductItem item = new ProductItem();
            item.setItemId(itemId);
            list.add(item);
        }
        product.setProductItemIdList(list);

        return productDao.add(product);
    }

    @Override
    public List<Product> getAllProducts(String restaurantId) {
        return productDao.getAllByRestaurant(restaurantId);
    }
}
