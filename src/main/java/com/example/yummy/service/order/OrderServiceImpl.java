package com.example.yummy.service.order;

import com.example.yummy.dao.OrderDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderState;
import com.example.yummy.util.OrderTimerUtil;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = DaoFactory.getOrderDao();

    @Override
    public boolean place(Order order) {
        boolean result = orderDao.add(order);
        new Thread(new OrderTimerUtil(order.getId())).start();

        return result;
    }

    @Override
    public boolean pay(int orderId) {
        OrderPaymentDealer dealer = new OrderPaymentDealer();
        return dealer.payOrder(orderId);
    }

    @Override
    public boolean withdraw(int orderId) {
        OrderPaymentDealer dealer = new OrderPaymentDealer();
        return dealer.withdrawOrder(orderId);
    }

    @Override
    public boolean complete(int orderId) {
        Order order = orderDao.get(orderId);
        order.setState(OrderState.COMPLETED);
        return orderDao.modify(order);
    }

    @Override
    public List<Order> getAllDeliveringOrders(String memberId) {
        return orderDao.getAllDeliveringOrder(memberId);
    }

    @Override
    public List<Order> getAllOrdersOfThisRestaurant(String restaurantId) {
        return orderDao.getAllOrdersOfThisRestaurant(restaurantId);
    }
}
