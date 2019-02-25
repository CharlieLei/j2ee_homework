package com.example.yummy.controller;

import com.example.yummy.model.product.FoodCombination;
import com.example.yummy.model.product.FoodItem;
import com.example.yummy.service.food.FoodCombinationService;
import com.example.yummy.service.food.FoodItemService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/food")
public class FoodController {

    @Autowired
    private FoodItemService itemService;
    @Autowired
    private FoodCombinationService combinationService;

    private Gson gson = new GsonBuilder().create();

    @RequestMapping(value = "/foodItem/add")
    public boolean addFoodItem(@RequestParam(value = "foodItem") String foodItemJson) {
        FoodItem foodItem = gson.fromJson(foodItemJson, FoodItem.class);
        return itemService.add(foodItem);
    }

    @RequestMapping(value = "/foodItem/modify")
    public boolean modifyFoodItem(@RequestParam(value = "foodItem") String foodItemJson) {
        FoodItem foodItem = gson.fromJson(foodItemJson, FoodItem.class);
        return itemService.modify(foodItem);
    }

    @RequestMapping(value = "/foodItem/get")
    public FoodItem getFoodItem(@RequestParam(value = "itemId") int itemId) {
        return itemService.get(itemId);
    }

    @RequestMapping(value = "/foodItem/getAllByRestaurant")
    public List<FoodItem> getAllFoodItemsByRestaurant(@RequestParam(value = "restaurantId") String restaurantId) {
        return itemService.getAllByRestaurant(restaurantId);
    }

    @RequestMapping(value = "/foodItem/getAllByRestaurantWithTime")
    public List<FoodItem> getAllFoodItemsByRestaurantWithTime(
            @RequestParam(value = "restaurantId") String restaurantId,
            @RequestParam(value = "startTime") Timestamp startTime,
            @RequestParam(value = "endTime") Timestamp endTime
    ) {

        return itemService.getAllByRestaurant(restaurantId, startTime, endTime);
    }

    @RequestMapping(value = "/foodCombination/add")
    public boolean addFoodCombination(@RequestParam(value = "foodCombination") String foodCombinationJson) {
        foodCombinationJson = foodCombinationJson
                .replaceAll("%5B", "[")
                .replaceAll("%5D", "]");
        FoodCombination foodCombination = gson.fromJson(foodCombinationJson, FoodCombination.class);
        return combinationService.add(foodCombination);
    }

    @RequestMapping(value = "/foodCombination/modify")
    public boolean modifyFoodCombination(@RequestParam(value = "foodCombination") String foodCombinationJson) {
        FoodCombination foodCombination = gson.fromJson(foodCombinationJson, FoodCombination.class);
        return combinationService.modify(foodCombination);
    }

    @RequestMapping(value = "/foodCombination/get")
    public FoodCombination getFoodCombination(@RequestParam(value = "combinationId") int combinationId) {
        return combinationService.get(combinationId);
    }

    @RequestMapping(value = "/foodCombination/getAllByRestaurant")
    public List<FoodCombination> getAllFoodCombinationsByRestaurant(@RequestParam(value = "restaurantId") String restaurantId) {
        return combinationService.getAllByRestaurant(restaurantId);
    }

    @RequestMapping(value = "/foodCombination/getAllByRestaurantWithTime")
    public List<FoodCombination> getAllFoodCombinationsByRestaurantWithTime(
            @RequestParam(value = "restaurantId") String restaurantId,
             @RequestParam(value = "startTime") Timestamp startTime,
             @RequestParam(value = "endTime") Timestamp endTime
    ) {
        return combinationService.getAllByRestaurant(restaurantId, startTime, endTime);
    }
}
