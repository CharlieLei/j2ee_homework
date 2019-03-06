package com.example.yummy.controller;

import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantInfoChange;
import com.example.yummy.service.examineInfoChange.ExamineInfoChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/examineRestaurant")
public class ExamineRestaurantController {

    @Autowired
    private ExamineInfoChangeService examineInfoChangeService;

    @RequestMapping(value = "/getAllUnexaminedRestaurantInfoChanges", method = RequestMethod.GET)
    public List<RestaurantInfoChange> getAllUnexaminedRestaurantInfoChanges() {
        return examineInfoChangeService.getAllUnexaminedRestaurantInfoChanges();
    }

    @RequestMapping(value = "/approveRestaurantInfoChange", method = RequestMethod.GET)
    public boolean approveRestaurantInfoChange(@RequestParam(value = "id") int id,
                                               @RequestParam(value = "restaurantId") String restaurantId) {
        return examineInfoChangeService.approveRestaurantInfoChange(id, restaurantId);
    }

    @RequestMapping(value = "/disapproveRestaurantInfoChange", method = RequestMethod.GET)
    public boolean disapproveRestaurantInfoChange(@RequestParam(value = "id") int id,
                                                  @RequestParam(value = "restaurantId") String restaurantId) {
        return examineInfoChangeService.disapproveRestaurantInfoChange(id, restaurantId);
    }

    @RequestMapping(value = "/getAllUnactivatedRestaurants", method = RequestMethod.GET)
    public List<Restaurant> getAllUnactivatedRestaurants() {
        return examineInfoChangeService.getAllUnactivatedRestaurants();
    }

    @RequestMapping(value = "/approveRestaurant", method = RequestMethod.GET)
    public boolean approveRestaurant(@RequestParam(value = "restaurantId") String restaurantId) {
        return examineInfoChangeService.approveRestaurant(restaurantId);
    }

    @RequestMapping(value = "/disapproveRestaurant", method = RequestMethod.GET)
    public boolean disapproveRestaurant(@RequestParam(value = "restaurantId") String restaurantId) {
        return examineInfoChangeService.disapproveRestaurant(restaurantId);
    }
}
