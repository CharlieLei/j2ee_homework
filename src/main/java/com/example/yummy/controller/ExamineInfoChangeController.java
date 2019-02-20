package com.example.yummy.controller;

import com.example.yummy.factory.ServiceFactory;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantInfoChange;
import com.example.yummy.service.examineInfoChange.ExamineInfoChangeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/examineInfoChange")
public class ExamineInfoChangeController {

    private ExamineInfoChangeService examineInfoChangeService = ServiceFactory.getExamineInfoChangeService();

    @RequestMapping(value = "/getAllUnexaminedRestaurantInfoChanges", method = RequestMethod.GET)
    public List<RestaurantInfoChange> getAllUnexaminedRestaurantInfoChanges() {
        return examineInfoChangeService.getAllUnexaminedRestaurantInfoChanges();
    }

    @RequestMapping(value = "/approveRestaurantInfoChange", method = RequestMethod.GET)
    public boolean approveRestaurantInfoChange(@RequestParam(value = "restaurantId") String restaurantId) {
        return examineInfoChangeService.approveRestaurantInfoChange(restaurantId);
    }
}
