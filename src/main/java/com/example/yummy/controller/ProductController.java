package com.example.yummy.controller;

import com.example.yummy.factory.ServiceFactory;
import com.example.yummy.model.product.Product;
import com.example.yummy.service.product.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public boolean addProduct(@RequestParam(value = "product") Product product) {
        ProductService productService = ServiceFactory.getProductService();
        return productService.add(product);
    }

    @RequestMapping(value = "/getAllProductsOfThisRestaurant", method = RequestMethod.GET)
    public List<Product> getAllProductsOfThisRestaurant(@RequestParam(value = "restaurantId") String restaurantId) {
        ProductService productService = ServiceFactory.getProductService();
        return productService.getAllProducts(restaurantId);
    }
}
