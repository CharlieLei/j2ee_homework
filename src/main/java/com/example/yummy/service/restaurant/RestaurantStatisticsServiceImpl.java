package com.example.yummy.service.restaurant;

import com.example.yummy.dao.MemberDao;
import com.example.yummy.dao.OrderDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.member.MemberLevel;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderState;
import com.example.yummy.model.restaurant.RestaurantType;
import com.example.yummy.model.statistics.RestaurantStatistics;
import com.example.yummy.model.yummyBill.YummyBill;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RestaurantStatisticsServiceImpl implements RestaurantStatisticsService {

    private OrderDao orderDao = DaoFactory.getOrderDao();
    private MemberDao memberDao = DaoFactory.getMemberDao();

    private static final long intervalMills = 24 * 60 * 60 * 1000;

    @Override
    public RestaurantStatistics get(String restaurantId,
                                    Timestamp startTime, Timestamp endTime,
                                    double lowerAmount, double upperAmount,
                                    MemberLevel memberLevel) {

        RestaurantStatistics statistics = new RestaurantStatistics();

        int completedOrderAmount = this.getOrderAmount(
                restaurantId,
                startTime, endTime,
                lowerAmount, upperAmount,
                memberLevel, OrderState.COMPLETED
        );
        statistics.setCompletedOrderAmount(completedOrderAmount);

        int withdrawnOrderAmount = this.getOrderAmount(
                restaurantId,
                startTime, endTime,
                lowerAmount, upperAmount,
                memberLevel, OrderState.CANCELLED
        );
        statistics.setWithdrawnOrderAmount(withdrawnOrderAmount);

        List<Double> incomePerDay = new ArrayList<>();
        Timestamp currentTime = startTime;
        Timestamp newDayTime = new Timestamp(currentTime.getTime() + intervalMills);
        while (newDayTime.getTime() <= endTime.getTime()) {
            List<Order> orderList = orderDao.getAllOrdersOfThisRestaurant(
                    restaurantId,
                    currentTime, newDayTime,
                    lowerAmount, upperAmount,
                    null
            );

            double income = 0;
            for (Order order: orderList) {
                Member member = memberDao.get(order.getMemberId());

                if (memberLevel == null || member.getMemberInfo().getLevel() == memberLevel) {
                    income += (order.getTotalAmount() - order.getRefund());
                }
            }
            incomePerDay.add(income);

            currentTime = new Timestamp(currentTime.getTime() + intervalMills);
            newDayTime = new Timestamp(currentTime.getTime() + intervalMills);
        }
        statistics.setIncomePerDay(incomePerDay);

        return statistics;
    }

    private int getOrderAmount(String restaurantId,
                               Timestamp startTime, Timestamp endTime,
                               double lowerAmount, double upperAmount,
                               MemberLevel memberLevel, OrderState orderState) {
        List<Order> orderList = orderDao.getAllOrdersOfThisRestaurant(
                restaurantId,
                startTime, endTime,
                lowerAmount, upperAmount,
                orderState
        );
        int orderAmount = 0;
        for (Order order: orderList) {
            Member member = memberDao.get(order.getMemberId());

            if (memberLevel == null || member.getMemberInfo().getLevel() == memberLevel) {
                orderAmount++;
            }
        }
        return orderAmount;
    }
}
