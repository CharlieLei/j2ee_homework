package com.example.yummy.dao;

import com.example.yummy.model.coupon.Coupon;

import java.util.List;

public interface CouponDao {
    public boolean add(Coupon coupon);

    public List<Coupon> getCouponListByRestaurant(String restaurantId);

    public List<Coupon> getCouponListByMember(String memberId);
}
