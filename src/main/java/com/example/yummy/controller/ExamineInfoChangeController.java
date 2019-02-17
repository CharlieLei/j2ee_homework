package com.example.yummy.controller;

import com.example.yummy.factory.ServiceFactory;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.service.examineInfoChange.ExamineInfoChangeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/examineInfoChange")
public class ExamineInfoChangeController {

    @RequestMapping(value = "/getAllRestaurantInfoChanges", method = RequestMethod.GET)
    public List<Restaurant> getAllRestaurantInfoChanges() {
        ExamineInfoChangeService examineInfoChangeService = ServiceFactory.getExamineInfoChangeService();
        return examineInfoChangeService.getAllRestaurantInfoChanges();
    }

    @RequestMapping(value = "/approveRestaurantInfoChange", method = RequestMethod.GET)
    public boolean approveRestaurantInfoChange(@RequestParam(value = "restaurantId") String restaurantId) {
        ExamineInfoChangeService examineInfoChangeService = ServiceFactory.getExamineInfoChangeService();
        return examineInfoChangeService.approveRestaurantInfoChange(restaurantId);
    }
}
