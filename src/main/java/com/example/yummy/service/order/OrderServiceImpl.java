package com.example.yummy.service.order;

import com.example.yummy.dao.OrderDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.Address;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderItem;
import com.example.yummy.model.order.OrderState;
import com.example.yummy.model.order.OrderVO;
import com.example.yummy.util.OrderTimerUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = DaoFactory.getOrderDao();

    @Override
    public boolean place(String memberId, String restaurantId,
                         Address senderAddr, Address receiverAddr,
                         OrderItem[] orderItemList, double totalAmount) {

        Order order = new Order();
        order.setMemberId(memberId);
        order.setRestaurantId(restaurantId);
        order.setSenderAddr(senderAddr);
        order.setReceiverAddr(receiverAddr);

        List<OrderItem> list = new ArrayList<>(Arrays.asList(orderItemList));
        order.setOrderItemList(list);

        order.setPlacingOrderTime(new Timestamp(System.currentTimeMillis()));

        order.setTotalAmount(totalAmount);
        order.setState(OrderState.PAYING);


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
    public boolean cancel(int orderId) {
        Order order = orderDao.get(orderId);
        order.setState(OrderState.CANCELLED);
        return orderDao.modify(order);
    }

    @Override
    public boolean withdraw(int orderId) {
        OrderPaymentDealer dealer = new OrderPaymentDealer();
        return dealer.withdrawOrder(orderId);
    }

    @Override
    public boolean complete(int orderId) {
        Order order = orderDao.get(orderId);
        order.setFulfillingOrderTime(new Timestamp(System.currentTimeMillis()));
        order.setState(OrderState.COMPLETED);
        return orderDao.modify(order);
    }

    @Override
    public List<OrderVO> getAllOrders(String memberId, OrderState orderState) {
        List<Order> orderList = orderDao.getAllOrder(memberId, orderState);
        return toOrderVoList(orderList);
    }

    @Override
    public List<OrderVO> getAllOrdersOfThisRestaurant(String restaurantId) {
        List<Order> orderList = orderDao.getAllOrdersOfThisRestaurant(restaurantId);
        return toOrderVoList(orderList);
    }

    private List<OrderVO> toOrderVoList(List<Order> orderList) {
        List<OrderVO> orderVOList = new ArrayList<>();
        for (Order order: orderList) {
            OrderVO orderVO = new OrderVO(order);
            orderVOList.add(orderVO);
        }
        return orderVOList;
    }
}
