package com.example.yummy.controller;

import com.example.yummy.factory.ServiceFactory;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.statistics.RestaurantStatistics;
import com.example.yummy.service.restaurant.RestaurantAccountService;
import com.example.yummy.service.restaurant.RestaurantService;
import com.example.yummy.service.restaurant.RestaurantStatisticsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @RequestMapping(value = "/modifyInfo", method = RequestMethod.GET)
    public boolean modifyInfo(@RequestParam(value = "restaurantId") Restaurant restaurant) {
        RestaurantAccountService restaurantAccountService = ServiceFactory.getRestaurantAccountService();
        return restaurantAccountService.modifyInfo(restaurant);
    }

    @RequestMapping(value = "/getRestaurant", method = RequestMethod.GET)
    public Restaurant getRestaurant(@RequestParam(value = "restaurantId") String restaurantId) {
        RestaurantService restaurantService = ServiceFactory.getRestaurantService();
        return restaurantService.get(restaurantId);
    }

    @RequestMapping(value = "/getStatistics", method = RequestMethod.GET)
    public RestaurantStatistics getStatistics(@RequestParam(value = "restaurantId") String restaurantId) {
        RestaurantStatisticsService restaurantStatisticsService = ServiceFactory.getRestaurantStatisticsService();
        return restaurantStatisticsService.get(restaurantId);
    }
}
