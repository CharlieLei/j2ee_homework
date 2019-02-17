package com.example.yummy.controller;

import com.example.yummy.model.restaurant.Restaurant;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/examineInfoChange")
public class ExamineInfoChangeController {

    @RequestMapping(value = "/getAllRestaurantInfoChanges", method = RequestMethod.GET)
    public List<Restaurant> getAllRestaurantInfoChanges() {
        return null;
    }

    @RequestMapping(value = "/approveRestaurantInfoChange", method = RequestMethod.GET)
    public boolean approveRestaurantInfoChange(String restaurantId) {
        return false;
    }
}
