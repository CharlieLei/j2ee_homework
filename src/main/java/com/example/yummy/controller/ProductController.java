package com.example.yummy.controller;

import com.example.yummy.model.product.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public boolean addProduct(@RequestParam(value = "product") Product product) {
        return false;
    }

    @RequestMapping(value = "/getAllProductsOfThisRestaurant")
    public List<Product> getAllProductsOfThisRestaurant(@RequestParam(value = "restaurantId") String restaurantId) {
        return null;
    }
}
