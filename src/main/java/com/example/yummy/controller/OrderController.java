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

    private OrderService orderService = ServiceFactory.getOrderService();

    @RequestMapping(value = "/place", method = RequestMethod.GET)
    public boolean placeOrder(@RequestParam(value = "order") Order order) {
        return orderService.place(order);
    }

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public boolean payOrder(@RequestParam(value = "orderId") int orderId) {
        return orderService.pay(orderId);
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public boolean withdrawOrder(@RequestParam(value = "orderId") int orderId) {
        return orderService.withdraw(orderId);
    }

    @RequestMapping(value = "/complete", method = RequestMethod.GET)
    public boolean completeOrder(@RequestParam(value = "orderId") int orderId) {
        return orderService.complete(orderId);
    }

    @RequestMapping(value = "/getAllDeliveringOrdersOfThisMember", method = RequestMethod.GET)
    public List<Order> getAllDeliveringOrdersOfThisMember(@RequestParam(value = "memberId")String memberId) {
        return orderService.getAllDeliveringOrders(memberId);
    }

    @RequestMapping(value = "/getAllOrdersOfThisRestaurant", method = RequestMethod.GET)
    public List<Order> getAllOrdersOfThisRestaurant(@RequestParam(value = "restaurantId") String restaurantId) {
        return orderService.getAllOrdersOfThisRestaurant(restaurantId);
    }
}
