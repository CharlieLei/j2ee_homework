package com.example.yummy.service.order;

import com.example.yummy.dao.MemberDao;
import com.example.yummy.dao.OrderDao;
import com.example.yummy.dao.RestaurantDao;
import com.example.yummy.dao.YummyDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderState;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.yummyBill.YummyBill;
import com.example.yummy.util.OrderTimerUtil;

import java.sql.Timestamp;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = DaoFactory.getOrderDao();
    private MemberDao memberDao = DaoFactory.getMemberDao();
    private YummyDao yummyDao = DaoFactory.getYummyDao();

    @Override
    public boolean place(Order order) {
        boolean result = orderDao.add(order);
        new Thread(new OrderTimerUtil(order.getId())).start();

        return result;
    }

    @Override
    synchronized public boolean pay(int orderId) {
        Order order = orderDao.get(orderId);
        Member member = memberDao.get(order.getMemberId());

        order.setRefund(0);
        order.setState(OrderState.DELIVERING);
        orderDao.modify(order);

        double totalAmount = order.getTotalAmount();
        member.setBalance(member.getBalance() - totalAmount);
        memberDao.modify(member);

        YummyBill yummyBill = new YummyBill();
        yummyBill.setTradingDate(new Timestamp(System.currentTimeMillis()));
        yummyBill.setOrderId(order.getId());
        yummyBill.setSettled(false);

        return yummyDao.add(yummyBill);
    }

    @Override
    public boolean withdraw(int orderId) {
        return false;
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
