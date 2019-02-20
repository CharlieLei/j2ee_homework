package com.example.yummy.controller;

import com.example.yummy.factory.ServiceFactory;
import com.example.yummy.model.Address;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantInfo;
import com.example.yummy.model.restaurant.RestaurantType;
import com.example.yummy.model.statistics.RestaurantStatistics;
import com.example.yummy.service.restaurant.RestaurantAccountService;
import com.example.yummy.service.restaurant.RestaurantService;
import com.example.yummy.service.restaurant.RestaurantStatisticsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    public boolean modifyInfo(@RequestParam(value = "restaurantId") String restaurantId,
                              @RequestParam(value = "restaurantInfo") String restaurantInfoJson) {

        Gson gson = new GsonBuilder().create();
        RestaurantInfo restaurantInfo = gson.fromJson(restaurantInfoJson, RestaurantInfo.class);
        return restaurantAccountService.modifyInfo(restaurantId, restaurantInfo);
    }

    @RequestMapping(value = "/modifyAddress", method = RequestMethod.GET)
    public boolean modifyAddress(@RequestParam(value = "restaurantId") String restaurantId,
                                 @RequestParam(value = "address") String addressJson) {
        Gson gson = new GsonBuilder().create();
        Address address = gson.fromJson(addressJson, Address.class);
        return restaurantAccountService.modifyAddress(restaurantId, address);
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
