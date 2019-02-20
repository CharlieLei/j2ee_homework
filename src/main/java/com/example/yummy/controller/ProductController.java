package com.example.yummy.controller;

import com.example.yummy.factory.ServiceFactory;
import com.example.yummy.model.product.Product;
import com.example.yummy.service.product.ProductService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private ProductService productService = ServiceFactory.getProductService();

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public boolean addProduct(@RequestParam(value = "restaurantId") String restaurantId,
                              @RequestParam(value = "name") String name,
                              @RequestParam(value = "quantity") int quantity,
                              @RequestParam(value = "price") double price,
                              @RequestParam(value = "itemIdList") String itemIdListJson) {

        itemIdListJson = itemIdListJson
                .replace("%5B", "[")
                .replace("%5D", "]");
        Gson gson = new GsonBuilder().create();
        int[] itemIdList = gson.fromJson(itemIdListJson, int[].class);
        return productService.add(restaurantId, name, quantity, price, itemIdList);
    }

    @RequestMapping(value = "/getAllProductsOfThisRestaurant", method = RequestMethod.GET)
    public List<Product> getAllProductsOfThisRestaurant(@RequestParam(value = "restaurantId") String restaurantId) {
        return productService.getAllProducts(restaurantId);
    }
}
