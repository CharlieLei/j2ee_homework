package com.example.yummy.util;

import com.example.yummy.dao.order.OrderDao;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

public class OrderTimerUtil implements Runnable {

    private OrderDao orderDao;

    private static final long waitingMillis = 2 * 60 * 1000;//15 * 60 * 1000;
    private static final long intervalMillis = 1000;

    private int orderId;

    public void init(int orderId, OrderDao orderDao) {
        this.orderId = orderId;
        this.orderDao = orderDao;
    }

    @Override
    public void run() {
        Order order = orderDao.get(orderId);
        order.setPayDeadline(new Timestamp(order.getPlacingOrderTime().getTime() + waitingMillis));
        orderDao.modify(order);

        long startTime = System.currentTimeMillis();
        long currentTime = System.currentTimeMillis();
        while (currentTime - startTime <= waitingMillis) {
            System.out.println("timing:" + orderId + " | times:" + currentTime + " | remain:" + (currentTime - startTime));

            try {
                Thread.sleep(intervalMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            currentTime = System.currentTimeMillis();
        }

        order = orderDao.get(orderId);
        if (order.getState() == OrderState.PAYING){
            order.setRefund(order.getTotalAmount());
            order.setState(OrderState.OVERDUE);
            orderDao.modify(order);
        }
    }
}
