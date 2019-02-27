package com.example.yummy.service.order;

import com.example.yummy.dao.member.MemberDao;
import com.example.yummy.dao.order.OrderDao;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.member.MemberLevel;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderState;
import com.example.yummy.util.OrderTimerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private MemberDao memberDao;

    @Override
    public Order get(int orderId) {
        return orderDao.get(orderId);
    }

    @Override
    public boolean place(Order order) {
        order.setPlacingOrderTime(new Timestamp(System.currentTimeMillis()));
        order.setState(OrderState.PAYING);

        boolean result = orderDao.add(order);
        OrderTimerUtil util = new OrderTimerUtil();
        util.init(order.getId(), orderDao);

        new Thread(util).start();

        return result;
    }

    @Override
    public boolean cancel(int orderId) {
        Order order = orderDao.get(orderId);
        order.setState(OrderState.CANCELLED);
        return orderDao.modify(order);
    }

    @Override
    public boolean complete(int orderId) {
        Order order = orderDao.get(orderId);
        order.setFulfillingOrderTime(new Timestamp(System.currentTimeMillis()));
        order.setState(OrderState.COMPLETED);
        boolean result = orderDao.modify(order);

        Member member = memberDao.get(order.getMemberId());
        this.refreshMemberLevel(member);

        return result;
    }

    @Override
    public List<Order> getAllOrders(String memberId, OrderState orderState) {
        return orderDao.getAllOrder(memberId, orderState);
    }

    @Override
    public List<Order> getAllOrdersOfThisRestaurant(String restaurantId, OrderState orderState) {
        return orderDao.getAllOrdersOfThisRestaurant(restaurantId, orderState);
    }

    private void refreshMemberLevel(Member member) {
        List<Order> list = orderDao.getAllOrder(member.getId(), OrderState.COMPLETED);

        if (list.size() >= 3) {
            member.getMemberInfo().setLevel(MemberLevel.GOLD);
        }else if (list.size() >= 2) {
            member.getMemberInfo().setLevel(MemberLevel.SILVER);
        }else {
            member.getMemberInfo().setLevel(MemberLevel.COPPER);
        }
        memberDao.modify(member);
    }
}
