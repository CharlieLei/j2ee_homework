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

    private ProductService productService = ServiceFactory.getProductService();

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public boolean addProduct(@RequestParam(value = "product") Product product) {
        return productService.add(product);
    }

    @RequestMapping(value = "/getAllProductsOfThisRestaurant", method = RequestMethod.GET)
    public List<Product> getAllProductsOfThisRestaurant(@RequestParam(value = "restaurantId") String restaurantId) {
        return productService.getAllProducts(restaurantId);
    }
}
