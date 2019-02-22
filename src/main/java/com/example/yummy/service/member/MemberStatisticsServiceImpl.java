package com.example.yummy.service.member;

import com.example.yummy.dao.MemberDao;
import com.example.yummy.dao.OrderDao;
import com.example.yummy.dao.RestaurantDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderState;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantType;
import com.example.yummy.model.statistics.MemberStatistics;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MemberStatisticsServiceImpl implements MemberStatisticsService {

    private OrderDao orderDao = DaoFactory.getOrderDao();
    private RestaurantDao restaurantDao = DaoFactory.getRestaurantDao();

    private static final long intervalMills = 24 * 60 * 60 * 1000;

    @Override
    public MemberStatistics get(String memberId,
                                Timestamp startTime, Timestamp endTime,
                                double lowerAmount, double upperAmount,
                                RestaurantType restaurantType) {

        MemberStatistics statistics = new MemberStatistics();

        int completedOrderAmount = this.getOrderAmount(
                memberId,
                startTime, endTime,
                lowerAmount, upperAmount,
                restaurantType, OrderState.COMPLETED
        );
        statistics.setCompletedOrderAmount(completedOrderAmount);

        int withdrawnOrderAmount = this.getOrderAmount(
                memberId,
                startTime, endTime,
                lowerAmount, upperAmount,
                restaurantType, OrderState.CANCELLED
        );
        statistics.setWithdrawnOrderAmount(withdrawnOrderAmount);

        List<Double> expensePerDay = new ArrayList<>();
        Timestamp currentTime = startTime;
        Timestamp newDayTime = new Timestamp(currentTime.getTime() + intervalMills);
        while (newDayTime.getTime() <= endTime.getTime()) {
            List<Order> orderList = orderDao.getAllOrder(
                    memberId,
                    currentTime, newDayTime,
                    lowerAmount, upperAmount,
                    null
            );

            double expense = 0;
            for (Order order: orderList) {
                Restaurant restaurant = restaurantDao.get(order.getRestaurantId());

                if (restaurantType == null || restaurant.getRestaurantInfo().getRestaurantType() == restaurantType) {
                    expense += (order.getTotalAmount() - order.getRefund());
                }
            }
            expensePerDay.add(expense);

            currentTime = new Timestamp(currentTime.getTime() + intervalMills);
            newDayTime = new Timestamp(currentTime.getTime() + intervalMills);
        }
        statistics.setExpensePerDay(expensePerDay);

        return statistics;
    }

    private int getOrderAmount(String memberId,
                               Timestamp startTime, Timestamp endTime,
                               double lowerAmount, double upperAmount,
                               RestaurantType restaurantType, OrderState orderState) {
        List<Order> orderList = orderDao.getAllOrder(
                memberId,
                startTime, endTime,
                lowerAmount, upperAmount,
                orderState
        );
        int orderAmount = 0;
        for (Order order: orderList) {
            Restaurant restaurant = restaurantDao.get(order.getRestaurantId());

            if (restaurantType == null || restaurant.getRestaurantInfo().getRestaurantType() == restaurantType) {
                orderAmount++;
            }
        }
        return orderAmount;
    }
}
