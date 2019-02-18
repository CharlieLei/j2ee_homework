package com.example.yummy.service.order;

import com.example.yummy.dao.MemberDao;
import com.example.yummy.dao.OrderDao;
import com.example.yummy.dao.YummyDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderState;
import com.example.yummy.model.yummyBill.YummyBill;

import java.sql.Timestamp;

class OrderPaymentDealer {

    private OrderDao orderDao = DaoFactory.getOrderDao();
    private MemberDao memberDao = DaoFactory.getMemberDao();
    private YummyDao yummyDao = DaoFactory.getYummyDao();

    synchronized boolean payOrder(int orderId) {
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

    synchronized boolean withdrawOrder(int orderId) {
        Order order = orderDao.get(orderId);
        Member member = memberDao.get(order.getMemberId());

        double totalAmount = order.getTotalAmount();
        order.setRefund(totalAmount);
        order.setState(OrderState.CANCELLED);
        orderDao.modify(order);

        member.setBalance(member.getBalance() + totalAmount);
        return memberDao.modify(member);
    }
}
