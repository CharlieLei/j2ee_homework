package com.example.yummy.controller;

import com.example.yummy.factory.ServiceFactory;
import com.example.yummy.model.Address;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderItem;
import com.example.yummy.model.order.OrderState;
import com.example.yummy.model.order.OrderVO;
import com.example.yummy.service.order.OrderService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private OrderService orderService = ServiceFactory.getOrderService();

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public OrderVO getOrder(@RequestParam(value = "orderId") int orderId) {
        return orderService.get(orderId);
    }

    @RequestMapping(value = "/place", method = RequestMethod.GET)
    public boolean placeOrder(@RequestParam(value = "memberId") String memberId,
                              @RequestParam(value = "restaurantId") String restaurantId,
                              @RequestParam(value = "senderAddr") String senderAddrJson,
                              @RequestParam(value = "receiverAddr") String receiverAddrJson,
                              @RequestParam(value = "orderItemList") String orderItemListJson,
                              @RequestParam(value = "totalAmount") double totalAmount) {

        orderItemListJson = orderItemListJson
                .replace("%5B", "[")
                .replace("%5D", "]");

        Gson gson = new GsonBuilder().create();
        Address senderAddr = gson.fromJson(senderAddrJson, Address.class);
        Address receiverAddr = gson.fromJson(receiverAddrJson, Address.class);
        OrderItem[] orderItemList = gson.fromJson(orderItemListJson, OrderItem[].class);

        return orderService.place(memberId, restaurantId, senderAddr, receiverAddr, orderItemList, totalAmount);
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
    public List<OrderVO> getAllPayingOrdersOfThisMember(@RequestParam(value = "memberId")String memberId) {
        return orderService.getAllOrders(memberId, OrderState.PAYING);
    }

    @RequestMapping(value = "/getAllDeliveringOrdersOfThisMember", method = RequestMethod.GET)
    public List<OrderVO> getAllDeliveringOrdersOfThisMember(@RequestParam(value = "memberId")String memberId) {
        return orderService.getAllOrders(memberId, OrderState.DELIVERING);
    }

    @RequestMapping(value = "/getAllNotActiveOrdersOfThisMember", method = RequestMethod.GET)
    public List<OrderVO> getAllNotActiveOrdersOfThisMember(@RequestParam(value = "memberId")String memberId) {
        return orderService.getAllOrders(memberId, OrderState.NOT_ACTIVE);
    }

    @RequestMapping(value = "/getAllNotActiveOrdersOfThisRestaurant", method = RequestMethod.GET)
    public List<OrderVO> getAllNotActiveOrdersOfThisRestaurant(@RequestParam(value = "restaurantId") String restaurantId) {
        return orderService.getAllOrdersOfThisRestaurant(restaurantId, OrderState.NOT_ACTIVE);
    }
}
