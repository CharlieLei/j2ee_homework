package com.example.yummy.service.coupon;

import com.example.yummy.model.coupon.Coupon;

import java.util.List;

public interface CouponService {

    public boolean addCoupon(String restaurantId, Coupon coupon);

    public List<Coupon> getCouponListByRestaurant( String restaurantId);

    public List<Coupon> getCouponListByMember(String memberId);
}
