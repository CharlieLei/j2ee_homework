package com.example.yummy.controller;

import com.example.yummy.factory.ServiceFactory;
import com.example.yummy.model.order.Order;
import com.example.yummy.service.order.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @RequestMapping(value = "/place", method = RequestMethod.GET)
    public boolean placeOrder(@RequestParam(value = "order") Order order) {
        OrderService orderService = ServiceFactory.getOrderService();
        return orderService.place(order);
    }

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public boolean payOrder(@RequestParam(value = "orderId") int orderId) {
        OrderService orderService = ServiceFactory.getOrderService();
        return orderService.pay(orderId);
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public boolean withdrawOrder(@RequestParam(value = "orderId") int orderId) {
        OrderService orderService = ServiceFactory.getOrderService();
        return orderService.withdraw(orderId);
    }

    @RequestMapping(value = "/getAllDeliveringOrdersOfThisMember", method = RequestMethod.GET)
    public List<Order> getAllDeliveringOrdersOfThisMember(@RequestParam(value = "memberId")String memberId) {
        OrderService orderService = ServiceFactory.getOrderService();
        return orderService.getAllDeliveringOrders(memberId);
    }

    @RequestMapping(value = "/getAllOrdersOfThisRestaurant", method = RequestMethod.GET)
    public List<Order> getAllOrdersOfThisRestaurant(@RequestParam(value = "restaurantId") String restaurantId) {
        OrderService orderService = ServiceFactory.getOrderService();
        return orderService.getAllOrdersOfThisRestaurant(restaurantId);
    }
}
