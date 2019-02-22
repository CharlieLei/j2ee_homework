package com.example.yummy.service.manager;

import com.example.yummy.dao.MemberDao;
import com.example.yummy.dao.RestaurantDao;
import com.example.yummy.dao.YummyDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantType;
import com.example.yummy.model.statistics.ManagerStatistics;
import com.example.yummy.model.yummyBill.YummyBill;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManagerStatisticsServiceImpl implements ManagerStatisticsService {

    private RestaurantDao restaurantDao = DaoFactory.getRestaurantDao();
    private MemberDao memberDao = DaoFactory.getMemberDao();
    private YummyDao yummyDao = DaoFactory.getYummyDao();

    private static final long intervalMills = 24 * 60 * 60 * 1000;

    @Override
    public ManagerStatistics get(Timestamp startTime, Timestamp endTime) {
        ManagerStatistics statistics = new ManagerStatistics();

        List<RestaurantType> restaurantTypeList = new ArrayList<>(Arrays.asList(RestaurantType.values()));
        List<Integer> restaurantAmountList = new ArrayList<>();
        for (RestaurantType type: RestaurantType.values()) {
            List<Restaurant> restaurantList = restaurantDao.getRestaurantsByType(type);
            restaurantAmountList.add(restaurantList.size());
        }
        statistics.setRestaurantTypeList(restaurantTypeList);
        statistics.setRestaurantAmountList(restaurantAmountList);

        List<Member> memberList = memberDao.getAll();
        statistics.setMemberAmount(memberList.size());

        List<Double> incomePerDay = new ArrayList<>();
        Timestamp currentTime = startTime;
        Timestamp newDayTime = new Timestamp(currentTime.getTime() + intervalMills);
        while (newDayTime.getTime() <= endTime.getTime()) {
            List<YummyBill> yummyBillList = yummyDao.getAllSettledBills(currentTime, newDayTime);

            double income = 0;
            for (YummyBill yummyBill: yummyBillList) {
                income += yummyBill.getSettleAmount();
            }
            incomePerDay.add(income);

            currentTime = new Timestamp(currentTime.getTime() + intervalMills);
            newDayTime = new Timestamp(currentTime.getTime() + intervalMills);
        }
        statistics.setIncomePerDay(incomePerDay);

        return statistics;
    }
}
