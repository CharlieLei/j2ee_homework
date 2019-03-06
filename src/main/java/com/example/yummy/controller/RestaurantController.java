package com.example.yummy.controller;

import com.example.yummy.model.Address;
import com.example.yummy.model.member.MemberLevel;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantInfo;
import com.example.yummy.model.restaurant.RestaurantType;
import com.example.yummy.model.statistics.RestaurantStatistics;
import com.example.yummy.service.restaurant.RestaurantAccountService;
import com.example.yummy.service.restaurant.RestaurantService;
import com.example.yummy.service.restaurant.RestaurantStatisticsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantAccountService restaurantAccountService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private RestaurantStatisticsService restaurantStatisticsService;

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
        return restaurantService.getActivatedRestaurantsByType(type);
    }

    @RequestMapping(value = "/getStatistics", method = RequestMethod.GET)
    public RestaurantStatistics getStatistics(@RequestParam(value = "restaurantId") String restaurantId,
                                              @RequestParam(value = "startTime") Timestamp startTime,
                                              @RequestParam(value = "endTime") Timestamp endTime,
                                              @RequestParam(value = "lowerAmount") double lowerAmount,
                                              @RequestParam(value = "upperAmount") double upperAmount,
                                              @RequestParam(value = "memberLevel") String memberLevelStr) {
        MemberLevel memberLevel = MemberLevel.getEnum(memberLevelStr);
        return restaurantStatisticsService.get(restaurantId, startTime, endTime, lowerAmount, upperAmount, memberLevel);
    }
}
