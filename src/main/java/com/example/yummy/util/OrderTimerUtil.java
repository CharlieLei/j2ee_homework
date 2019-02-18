package com.example.yummy.util;

import com.example.yummy.dao.OrderDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderState;

public class OrderTimerUtil implements Runnable {

    private OrderDao orderDao = DaoFactory.getOrderDao();
    private static final long waitingMillis = 15 * 60 * 1000;
    private static final long intervalMillis = 1000;

    private int orderId;

    public OrderTimerUtil(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        long currentTime = System.currentTimeMillis();
        while (currentTime - startTime <= waitingMillis) {
            System.out.println("timing:" + orderId + " | times:" + currentTime);
            Order order = orderDao.get(orderId);
            if (order.getState() != OrderState.PAYING){
                return;
            }

            try {
                Thread.sleep(intervalMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            currentTime = System.currentTimeMillis();
        }

        Order order = orderDao.get(orderId);
        order.setState(OrderState.OVERDUE);
    }
}
