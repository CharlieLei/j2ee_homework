package com.example.yummy.service.pay;

import com.example.yummy.dao.food.FoodCombinationDao;
import com.example.yummy.dao.food.FoodItemDao;
import com.example.yummy.dao.member.MemberDao;
import com.example.yummy.dao.order.OrderDao;
import com.example.yummy.dao.yummyBill.YummyDao;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.member.MemberLevel;
import com.example.yummy.model.order.*;
import com.example.yummy.model.product.FoodCombination;
import com.example.yummy.model.product.FoodItem;
import com.example.yummy.model.yummyBill.YummyBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private FoodItemDao foodItemDao;
    @Autowired
    private FoodCombinationDao foodCombinationDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private YummyDao yummyDao;

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

            List<OrderFoodItem> foodItemList = order.getOrderFoodItemList();
            for (OrderFoodItem orderFoodItem: foodItemList) {
                FoodItem foodItem = orderFoodItem.getFoodItem();
                foodItem.setQuantity(foodItem.getQuantity() - orderFoodItem.getAmount());
                foodItemDao.modify(foodItem);
            }

            List<OrderFoodCombination> foodCombinationList = order.getOrderFoodCombinationList();
            for (OrderFoodCombination orderFoodCombination: foodCombinationList) {
                FoodCombination foodCombination = orderFoodCombination.getFoodCombination();
                foodCombination.setQuantity(foodCombination.getQuantity() - orderFoodCombination.getAmount());
                foodCombinationDao.modify(foodCombination);
            }

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
        List<OrderFoodItem> foodItemList = order.getOrderFoodItemList();
        for (OrderFoodItem orderFoodItem: foodItemList) {
            FoodItem foodItem = orderFoodItem.getFoodItem();

            if (foodItem.getQuantity() < orderFoodItem.getAmount()) {
                return false;
            }
        }

        List<OrderFoodCombination> foodCombinationList = order.getOrderFoodCombinationList();
        for (OrderFoodCombination orderFoodCombination: foodCombinationList) {
            FoodCombination foodCombination = orderFoodCombination.getFoodCombination();

            if (foodCombination.getQuantity() < orderFoodCombination.getAmount()) {
                return false;
            }
        }
        return true;
    }
}
