package com.example.yummy.controller;

import com.example.yummy.model.restaurant.RestaurantInfoChange;
import com.example.yummy.service.examineInfoChange.ExamineInfoChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/examineInfoChange")
public class ExamineInfoChangeController {

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
}
