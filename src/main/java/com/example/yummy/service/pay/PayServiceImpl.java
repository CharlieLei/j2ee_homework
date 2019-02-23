package com.example.yummy.service.pay;

import com.example.yummy.dao.MemberDao;
import com.example.yummy.dao.OrderDao;
import com.example.yummy.dao.ProductDao;
import com.example.yummy.dao.YummyDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderItem;
import com.example.yummy.model.order.OrderState;
import com.example.yummy.model.product.Product;
import com.example.yummy.model.yummyBill.YummyBill;

import java.sql.Timestamp;
import java.util.List;

public class PayServiceImpl implements PayService {

    private OrderDao orderDao = DaoFactory.getOrderDao();
    private MemberDao memberDao = DaoFactory.getMemberDao();
    private YummyDao yummyDao = DaoFactory.getYummyDao();
    private ProductDao productDao = DaoFactory.getProductDao();

    private long withdrawThresholdMills = 5 * 60 * 1000;

    @Override
    public boolean isLoginInfoCorrect(String accountId, String password) {
        return memberDao.isLoginInfoCorrect(accountId, password);
    }

    @Override
    synchronized public boolean payOrder(String memberAccountId, int orderId) {
        Order order = orderDao.get(orderId);

        if (isProductsInOrderEnough(order)) {
            Member member = memberDao.get(order.getMemberId());

            order.setRefund(0);
            order.setState(OrderState.DELIVERING);
            orderDao.modify(order);

            double totalAmount = order.getTotalAmount();
            member.getMemberInfo().setBalance(member.getMemberInfo().getBalance() - totalAmount);
            memberDao.modify(member);

            YummyBill yummyBill = new YummyBill();
            yummyBill.setTradingDate(new Timestamp(System.currentTimeMillis()));
            yummyBill.setOrderId(order.getId());
            yummyBill.setSettled(false);

            return yummyDao.add(yummyBill);
        }else {
            double totalAmount = order.getTotalAmount();
            order.setRefund(totalAmount);
            order.setState(OrderState.NOT_ENOUGH_PRODUCT);
            orderDao.modify(order);

            return false;
        }
    }

    @Override
    synchronized public boolean withdrawOrder(int orderId) {
        Order order = orderDao.get(orderId);
        Member member = memberDao.get(order.getMemberId());

        double totalAmount = order.getTotalAmount();

        long currentMills = System.currentTimeMillis();
        long passTimeMills = currentMills - order.getPlacingOrderTime().getTime();
        if (passTimeMills < withdrawThresholdMills) {
            double refund = totalAmount * (1.0 - passTimeMills / (double)withdrawThresholdMills);

            order.setRefund(refund);
            order.setState(OrderState.CANCELLED);
            orderDao.modify(order);

            member.getMemberInfo().setBalance(member.getMemberInfo().getBalance() + refund);
            return memberDao.modify(member);
        }else {
            return false;
        }
    }

    private boolean isProductsInOrderEnough(Order order) {
        List<OrderItem> orderItemList = order.getOrderItemList();
        for (OrderItem item: orderItemList) {
            Product product = productDao.get(item.getProductId());

            if (product.getQuantity() < item.getItemAmount()) {
                return false;
            }
        }
        return true;
    }
}
