package com.example.yummy.controller;

import com.example.yummy.factory.ServiceFactory;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantType;
import com.example.yummy.model.statistics.RestaurantStatistics;
import com.example.yummy.service.restaurant.RestaurantAccountService;
import com.example.yummy.service.restaurant.RestaurantService;
import com.example.yummy.service.restaurant.RestaurantStatisticsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantAccountService restaurantAccountService = ServiceFactory.getRestaurantAccountService();
    private RestaurantService restaurantService = ServiceFactory.getRestaurantService();
    private RestaurantStatisticsService restaurantStatisticsService = ServiceFactory.getRestaurantStatisticsService();

    @RequestMapping(value = "/modifyInfo", method = RequestMethod.GET)
    public boolean modifyInfo(@RequestParam(value = "restaurantId") Restaurant restaurant) {
        return restaurantAccountService.modifyInfo(restaurant);
    }

    @RequestMapping(value = "/getRestaurant", method = RequestMethod.GET)
    public Restaurant getRestaurant(@RequestParam(value = "restaurantId") String restaurantId) {
        return restaurantService.get(restaurantId);
    }

    @RequestMapping(value = "/getRestaurantsByType", method = RequestMethod.GET)
    public List<Restaurant> getRestaurantsByType(@RequestParam(value = "restaurantType") String restaurantType) {
        RestaurantType type = RestaurantType.getEnum(restaurantType);
        return restaurantService.getRestaurantsByType(type);
    }

    @RequestMapping(value = "/getStatistics", method = RequestMethod.GET)
    public RestaurantStatistics getStatistics(@RequestParam(value = "restaurantId") String restaurantId) {
        return restaurantStatisticsService.get(restaurantId);
    }
}
