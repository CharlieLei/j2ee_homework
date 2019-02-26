package com.example.yummy.controller;

import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderState;
import com.example.yummy.service.order.OrderService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Order getOrder(@RequestParam(value = "orderId") int orderId) {
        return orderService.get(orderId);
    }

    @RequestMapping(value = "/place", method = RequestMethod.GET)
    public boolean placeOrder(@RequestParam(value = "order") String orderJson) {

        orderJson = orderJson
                .replace("%5B", "[")
                .replace("%5D", "]");

        Gson gson = new GsonBuilder().create();
        Order order = gson.fromJson(orderJson, Order.class);

        return orderService.place(order);
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    public boolean cancelOrder(@RequestParam(value = "orderId") int orderId) {
        return orderService.cancel(orderId);
    }

    @RequestMapping(value = "/complete", method = RequestMethod.GET)
    public boolean completeOrder(@RequestParam(value = "orderId") int orderId) {
        return orderService.complete(orderId);
    }

    @RequestMapping(value = "/getAllPayingOrdersOfThisMember", method = RequestMethod.GET)
    public List<Order> getAllPayingOrdersOfThisMember(@RequestParam(value = "memberId")String memberId) {
        return orderService.getAllOrders(memberId, OrderState.PAYING);
    }

    @RequestMapping(value = "/getAllDeliveringOrdersOfThisMember", method = RequestMethod.GET)
    public List<Order> getAllDeliveringOrdersOfThisMember(@RequestParam(value = "memberId")String memberId) {
        return orderService.getAllOrders(memberId, OrderState.DELIVERING);
    }

    @RequestMapping(value = "/getAllNotActiveOrdersOfThisMember", method = RequestMethod.GET)
    public List<Order> getAllNotActiveOrdersOfThisMember(@RequestParam(value = "memberId")String memberId) {
        return orderService.getAllOrders(memberId, OrderState.NOT_ACTIVE);
    }

    @RequestMapping(value = "/getAllNotActiveOrdersOfThisRestaurant", method = RequestMethod.GET)
    public List<Order> getAllNotActiveOrdersOfThisRestaurant(@RequestParam(value = "restaurantId") String restaurantId) {
        return orderService.getAllOrdersOfThisRestaurant(restaurantId, OrderState.NOT_ACTIVE);
    }
}
