package com.example.yummy.controller;

import com.example.yummy.model.order.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @RequestMapping(value = "/place", method = RequestMethod.GET)
    public boolean placeOrder(@RequestParam(value = "order") Order order) {
        return false;
    }

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public boolean payOrder(@RequestParam(value = "orderId") int orderId) {
        return false;
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public boolean withdrawOrder(@RequestParam(value = "orderId") int orderId) {
        return false;
    }

    @RequestMapping(value = "/getAllDeliveringOrdersOfThisMember", method = RequestMethod.GET)
    public List<Order> getAllDeliveringOrdersOfThisMember(@RequestParam(value = "memberId")String memberId) {
        return null;
    }

    @RequestMapping(value = "getAllOrdersOfThisRestaurant", method = RequestMethod.GET)
    public List<Order> getAllOrdersOfThisRestaurant(@RequestParam(value = "restaurantId") String restaurantId) {
        return null;
    }
}
