package com.example.yummy.controller;

import com.example.yummy.factory.ServiceFactory;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.service.restaurant.RestaurantInfoService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @RequestMapping(value = "getRestaurant", method = RequestMethod.GET)
    public Restaurant getRestaurant(@RequestParam(value = "restaurantId") String restaurantId) {
        RestaurantInfoService restaurantInfoService = ServiceFactory.getRestaurantInfoService();
        return restaurantInfoService.getRestaurant(restaurantId);
    }
}
